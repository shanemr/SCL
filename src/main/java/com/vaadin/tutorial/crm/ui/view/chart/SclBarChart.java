package com.vaadin.tutorial.crm.ui.view.chart;


import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class SclBarChart extends VerticalLayout {


        public SclBarChart(){
            addClassName("scl-chart");
            add(getSurveyChart());
        }

    private Chart getSurveyChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        chart.setWidth("100%");
        chart.setMinWidth("100%");
        Configuration configuration = chart.getConfiguration();
        configuration.setTitle("Most Recent SCL - 07/16/2021");
        configuration.addSeries(new ListSeries("Severity", 1, 1.5, 5, 4, 3, 5, 2, 2.5, 4.3));


        XAxis x = new XAxis();
        x.setCrosshair(new Crosshair());
        x.setCategories("Anxiety","Attention", "Compulsive", "Depression", "Executive", "Memory", "Pain", "Sensory","Social");
        configuration.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setMax(5);
        y.setTitle("Severity");
        configuration.addyAxis(y);

        Tooltip tooltip = new Tooltip();
        tooltip.setShared(true);
        configuration.setTooltip(tooltip);
        return chart;

    }


}
