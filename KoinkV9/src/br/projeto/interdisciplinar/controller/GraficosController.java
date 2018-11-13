package br.projeto.interdisciplinar.controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.projeto.interdisciplinar.dao.FinancaDAO;
import br.projeto.interdisciplinar.model.Despesa;
import br.projeto.interdisciplinar.model.Receita;
import br.projeto.interdisciplinar.model.Usuario;

@ManagedBean
public class GraficosController implements Serializable {

	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	private PieChartModel pieModel3;
	private PieChartModel pieModel4;
	private PieChartModel pieModel5;
	private BarChartModel animatedModel2;
	private LineChartModel lineModel2;

	@PostConstruct
	public void init() {
		createPieModels();
		createAnimatedModels();
		createLineModels();
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public PieChartModel getPieModel3() {
		return pieModel3;
	}

	public PieChartModel getPieModel4() {
		return pieModel4;
	}

	public PieChartModel getPieModel5() {
		return pieModel5;
	}

	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	private void createPieModels() {
		createPieModel1();
		// createPieModel2();
		createPieModel3();
		createPieModel4();
		createPieModel5();
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		FinancaDAO dao = new FinancaDAO();

		pieModel1.set("Receita", dao.receitaTotal(usuario.getId_Usuario()));
		pieModel1.set("Despesa", dao.despesaTotal(usuario.getId_Usuario()));

		pieModel1.setTitle("Receitas X Despesas - Total");
		pieModel1.setLegendPosition("w");
	}

	private void createPieModel2() {
		pieModel2 = new PieChartModel();

		pieModel2.set("Brand 1", 540);
		pieModel2.set("Brand 2", 325);
		pieModel2.set("Brand 3", 702);
		pieModel2.set("Brand 4", 421);

		pieModel2.setTitle("Custom Pie");
		pieModel2.setLegendPosition("w");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
	}

	private void createPieModel3() {

		pieModel3 = new PieChartModel();

		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		FinancaDAO dao = new FinancaDAO();

		pieModel3.set("Receita", dao.receitaTotalMes(usuario.getId_Usuario(), 1 + new Date().getMonth()));
		pieModel3.set("Despesa", dao.despesaTotalMes(usuario.getId_Usuario(), 1 + new Date().getMonth()));

		Calendar c = Calendar.getInstance();

		pieModel3.setTitle("Receitas X Despesas - Mês " + (c.get(Calendar.MONTH) + 1) + " de " + c.get(Calendar.YEAR));
		pieModel3.setLegendPosition("w");
	}

	private void createPieModel4() {

		pieModel4 = new PieChartModel();
		int flag = 0;

		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		List<Receita> lista = new FinancaDAO().listaTodasReceitas(usuario.getId_Usuario());
		List<String> listaString = new ArrayList<>();

		for (Receita r : lista) {
			if (!listaString.contains(r.getTipo().getTipo())) {
				listaString.add(r.getTipo().getTipo());

				pieModel4.set(r.getTipo().getTipo(),
						new FinancaDAO().totalPorTipoReceita(usuario.getId_Usuario(), r.getTipo().getId_Tipo()));

				flag++;
			}

		}

		if (flag == 0) {
			pieModel4.set("Ainda não há categorias!", 0);
		}

		pieModel4.setTitle("Receitas por categoria - Total");
		pieModel4.setLegendPosition("w");
	}

	private void createPieModel5() {

		pieModel5 = new PieChartModel();
		int flag = 0;

		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		List<Despesa> lista = new FinancaDAO().listaTodasDespesas(usuario.getId_Usuario());
		List<String> listaString = new ArrayList<>();

		for (Despesa d : lista) {
			if (!listaString.contains(d.getTipo().getTipo())) {
				listaString.add(d.getTipo().getTipo());

				pieModel5.set(d.getTipo().getTipo(),
						new FinancaDAO().totalPorTipoDespesa(usuario.getId_Usuario(), d.getTipo().getId_Tipo()));

				flag++;

			}

		}

		if (flag == 0) {
			pieModel5.set("Ainda não há categorias!", 0);
		}

		pieModel5.setTitle("Despesas por categoria - Total");
		pieModel5.setLegendPosition("w");
	}

	private void createAnimatedModels() {
		Calendar c = Calendar.getInstance();

		animatedModel2 = initBarModel();
		animatedModel2.setTitle("Despesa X Receita por mês no ano de " + c.get(Calendar.YEAR));
		animatedModel2.setAnimate(true);
		animatedModel2.setLegendPosition("ne");
		Axis yAxis = animatedModel2.getAxis(AxisType.Y);
		yAxis.setMin(0);

		// Ajustando máximo do gráfico
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (new FinancaDAO().receitaTotal(usuario.getId_Usuario()) == null
				&& new FinancaDAO().despesaTotal(usuario.getId_Usuario()) == null) {
			yAxis.setMax(100);

		} else if (new FinancaDAO().receitaTotal(usuario.getId_Usuario()) == null) {
			yAxis.setMax(new FinancaDAO().despesaTotal(usuario.getId_Usuario()) + 500);

		} else if (new FinancaDAO().despesaTotal(usuario.getId_Usuario()) == null) {
			yAxis.setMax(new FinancaDAO().receitaTotal(usuario.getId_Usuario()) + 500);

		} else if (new FinancaDAO().despesaTotal(usuario.getId_Usuario()) > new FinancaDAO()
				.receitaTotal(usuario.getId_Usuario())) {
			yAxis.setMax(new FinancaDAO().despesaTotal(usuario.getId_Usuario()) + 500);
		} else {
			yAxis.setMax(new FinancaDAO().receitaTotal(usuario.getId_Usuario()) + 500);
		}

	}

	private BarChartModel initBarModel() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		BarChartModel model = new BarChartModel();

		ChartSeries receitas = new ChartSeries();
		receitas.setLabel("Receitas");

		for (int i = 1; i <= 12; i++) {
			receitas.set(i, new FinancaDAO().receitaTotalMes(usuario.getId_Usuario(), i));
		}

		ChartSeries despesas = new ChartSeries();
		despesas.setLabel("Despesas");

		for (int i = 1; i <= 12; i++) {
			despesas.set(i, new FinancaDAO().despesaTotalMes(usuario.getId_Usuario(), i));
		}

		model.addSeries(receitas);
		model.addSeries(despesas);

		return model;
	}

	private void createLineModels() {
        Calendar c = Calendar.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Saldo do ano de " + c.get(Calendar.YEAR));
        lineModel2.setLegendPosition("ne");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Saldo");
        
        
        if (new FinancaDAO().receitaTotal(usuario.getId_Usuario()) == null
				&& new FinancaDAO().despesaTotal(usuario.getId_Usuario()) == null) {
			yAxis.setMax(100);
			yAxis.setMin(0);

		} else if (new FinancaDAO().receitaTotal(usuario.getId_Usuario()) == null) {
			yAxis.setMax(new FinancaDAO().despesaTotal(usuario.getId_Usuario()) + 500);
			 yAxis.setMin(- new FinancaDAO().despesaTotal(usuario.getId_Usuario()) - 500);

		} else if (new FinancaDAO().despesaTotal(usuario.getId_Usuario()) == null) {
			yAxis.setMax(new FinancaDAO().receitaTotal(usuario.getId_Usuario()) + 500);
			yAxis.setMin(- new FinancaDAO().receitaTotal(usuario.getId_Usuario()) - 500);

		} else if (new FinancaDAO().despesaTotal(usuario.getId_Usuario()) > new FinancaDAO()
				.receitaTotal(usuario.getId_Usuario())) {
			yAxis.setMax(new FinancaDAO().despesaTotal(usuario.getId_Usuario()) + 500);
			yAxis.setMin(- new FinancaDAO().despesaTotal(usuario.getId_Usuario()) - 500);
			
		} else {
			yAxis.setMax(new FinancaDAO().receitaTotal(usuario.getId_Usuario()) + 500);
			yAxis.setMin(- new FinancaDAO().receitaTotal(usuario.getId_Usuario()) - 500);
		}
    }
	
	private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries saldos = new ChartSeries();
        saldos.setLabel("Saldo durante o ano");
        saldos.set("Jan", obtemSaldo_Mes_Ano_Atual(1));
        saldos.set("Fev", obtemSaldo_Mes_Ano_Atual(2));
        saldos.set("Mar", obtemSaldo_Mes_Ano_Atual(3));
        saldos.set("Abr", obtemSaldo_Mes_Ano_Atual(4));
        saldos.set("Mai", obtemSaldo_Mes_Ano_Atual(5));
        saldos.set("Jun", obtemSaldo_Mes_Ano_Atual(6));
        saldos.set("Jul", obtemSaldo_Mes_Ano_Atual(7));
        saldos.set("Ago", obtemSaldo_Mes_Ano_Atual(8));
        saldos.set("Set", obtemSaldo_Mes_Ano_Atual(9));
        saldos.set("Out", obtemSaldo_Mes_Ano_Atual(10));
        saldos.set("Nov", obtemSaldo_Mes_Ano_Atual(11));
        saldos.set("Dez", obtemSaldo_Mes_Ano_Atual(12));
 
        model.addSeries(saldos);
         
        return model;
    }
	
	
	public double obtemSaldo_Mes_Ano_Atual(int data) {
		double saldo;
		Calendar c = Calendar.getInstance();

		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (new FinancaDAO().receitaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR)) != null
				&& new FinancaDAO().despesaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR)) != null) {

			saldo = new FinancaDAO().receitaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR))
					- new FinancaDAO().despesaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR));

		} else if (new FinancaDAO().receitaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR)) != null
				&& new FinancaDAO().despesaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR)) == null) {

			saldo = new FinancaDAO().receitaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR));

		} else if (new FinancaDAO().receitaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR)) == null
				&& new FinancaDAO().despesaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR)) != null) {
			
			saldo = new FinancaDAO().despesaTotalMes_Ano(usuario.getId_Usuario(), data, c.get(Calendar.YEAR));
		} else {
			
			saldo = 0;
		}

		return saldo;
	}

}
