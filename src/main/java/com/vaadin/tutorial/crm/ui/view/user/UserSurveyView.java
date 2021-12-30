package com.vaadin.tutorial.crm.ui.view.user;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class UserSurveyView extends AppLayout {
    // Constructor
    public UserSurveyView() {

        createHeader();
    }

    // Creating header fo layout
    private void createHeader() {
        H1 logo = new H1("Symptom Check List");
        logo.addClassName("logo");

        Anchor logout = new Anchor("logout", "Log out");

        DrawerToggle toggle = new DrawerToggle();
        createTog();
        HorizontalLayout header = new HorizontalLayout(toggle, logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }


    public void createTog(){
        Tabs tabs = getTabs();
        addToDrawer(tabs);
    }

    private Tabs getTabs(){
        Tabs tabs = new Tabs();
        tabs.add(
                createTab(VaadinIcon.HOME, "Home")
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        // Demo has no routes
        link.setRoute(UserHomeView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }



}