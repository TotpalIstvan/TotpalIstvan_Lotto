package com.example.totpalistvan_lotto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

public class HelloController {

        @FXML
        private Label szamLista;
        @FXML
        private Label randSzam;
        @FXML
        private Button btnSorsol;
        private Timer t;
        private List<Integer> szamok = new ArrayList<>();
        private int ciklus = 0;
        private boolean rendezE = false;


        @FXML
        public void sorsolBtnClick() {
            if (!rendezE) {
                szamLista.setText("");
                t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> { sorsol(); });
                    }
                }, 0, 150);
            } else {
                Collections.sort(szamok);
                szamLista.setText("");
                for (int szam : szamok) {
                   hozzaad(szam);
                }
                reset();
            }
        }

        private void sorsol() {
            if (ciklus < 50) {
                int szam = (int) (Math.random() * 90 + 1);
                randSzam.setText(szam + "");
                ciklus++;
                if (ciklus % 10 == 0) {
                    while (szamok.contains(szam)) {
                        szam = (int) (Math.random() * 90 + 1);
                        randSzam.setText(szam + "");
                    }
                    szamok.add(szam);
                    hozzaad(szam);
                }
            } else {
                t.cancel();
                btnSorsol.setText("Rendez");
                rendezE = true;
            }
        }

        private void reset() {
            rendezE = false;
            btnSorsol.setText("Sorsol");
            ciklus = 0;
            szamok.clear();
        }

        private void hozzaad(int szam) {
            String text = szamLista.getText();
            if (text.length() == 0) text += szam + "";
            else text += ", " + szam;
            szamLista.setText(text);
        }
}