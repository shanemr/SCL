package com.vaadin.tutorial.crm.ui.view.questionnaire;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class Pop extends VerticalLayout{
     private Button close = new Button("Close");
     private VerticalLayout popupContent = new VerticalLayout();
     private boolean b;
     public Pop(){

          popupContent.addComponentAsFirst(new Text("You must select an answer"));
          popupContent.addComponentAsFirst(close);
          close.addClickListener(event -> closePopUp(b));
          add(popupContent);

     }

     private void closePopUp(boolean b){
          close.addClickListener(event -> {
               popupContent.setVisible(b);
          });
     }

     public Button getClose() {
          return close;
     }

     public void setClose(Button close) {
          this.close = close;
     }

     public VerticalLayout getPopupContent() {
          return popupContent;
     }

     public void setPopupContent(VerticalLayout popupContent) {
          this.popupContent = popupContent;
     }

     public boolean isB() {
          return b;
     }

     public void setB(boolean b) {
          this.b = b;
     }
}
