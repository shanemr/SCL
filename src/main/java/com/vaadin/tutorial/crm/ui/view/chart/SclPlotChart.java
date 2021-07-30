package com.vaadin.tutorial.crm.ui.view.chart;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.charts.model.Legend;
import com.vaadin.flow.component.charts.model.LayoutDirection;
import com.vaadin.flow.component.charts.model.VerticalAlign;
import com.vaadin.flow.component.charts.model.HorizontalAlign;
import com.vaadin.flow.component.charts.model.PlotOptionsSeries;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SclPlotChart extends VerticalLayout {
    Chart chart = new Chart(ChartType.LINE);

    public SclPlotChart(){
        add(plotChart());
    }

    private Chart plotChart() {

        final Chart chart = new Chart();

        Configuration configuration = chart.getConfiguration();

        configuration.setTitle("Symptom Change Over Time");
        configuration.setSubTitle("Source: SCL");

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle("Severity");

        Legend legend = configuration.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setVerticalAlign(VerticalAlign.MIDDLE);
        legend.setAlign(HorizontalAlign.RIGHT);

        PlotOptionsSeries plotOptionsSeries = new PlotOptionsSeries();
        plotOptionsSeries.setPointStart(2010);
        configuration.setPlotOptions(plotOptionsSeries);

        configuration.addSeries(new ListSeries("Anxiety", null, null, 4, 3, 3, 1));
        configuration.addSeries(new ListSeries("Attention", 2, 5, 4, 2, 4, 3));
        configuration.addSeries(new ListSeries("Compulsive", 3, 5, 4, 1, 4, 1));
        configuration.addSeries(new ListSeries("Depression", 1, 2, 4, 2, 1, 3));
        configuration.addSeries(new ListSeries("Executive", 1, 3, 2, 2, 3, 4));
        configuration.addSeries(new ListSeries("Memory", 4, 5, 4, 2, 5, 3));
        configuration.addSeries(new ListSeries("Pain", 2, 5, 4, 3, 1, 2));
        configuration.addSeries(new ListSeries("Sensory", 1, 5, 4, 2, 2, 3));
        configuration.addSeries(new ListSeries("Social", 1, 5, 4, 5, 4, 5));


        return chart;
    }
}
