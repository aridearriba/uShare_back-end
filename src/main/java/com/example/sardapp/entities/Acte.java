package com.example.sardapp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actes")
public class Acte {

    @Id @Column(name = "id")                    private Integer id;
    @Column(name = "tipus")                     private String tipus;
    @Column(name = "dia")                       private Date dia;
    @Column(name = "hora1")                     private String hora1;
    @Column(name = "hora2")                     private String hora2;
    @Column(name = "hora3")                     private String hora3;
    //@Column(name = "destacat")                  private String destacat;
    @Column(name = "anul")                      private Boolean anul;
    //@Column(name = "mode")                      private String mode;
    @Column(name = "nomActivitat")              private String nomActivitat;
    //@Column(name = "nomiEstatActivitat")        private String nomiEstatActivitat;
    @Column(name = "mesDades")                  private String mesDades;
    @Column(name = "linkProgHTML")              private String linkProgHTML;
    @Column(name = "linkProgPDF")               private String linkProgPDF;
    @Column(name = "mesDadesLink")              private String mesDadesLink;
    @Column(name = "lloc")                      private String lloc;
    @Column(name = "latitud")                   private Integer latitud;
    @Column(name = "longitud")                  private Integer longitud;
    @Column(name = "latitudAprox")              private Integer latitudAprox;
    @Column(name = "longitudAprox")             private Integer longitudAprox;
    @Column(name = "llocSiPlou")                private String llocSiPlou;
    @Column(name = "latitudSiPlou")             private Integer latitudSiPlou;
    @Column(name = "longitudSiPlou")            private Integer longitudSiPlou;
    @Column(name = "latitudSiPlouAprox")        private Integer latitudSiPlouAprox;
    @Column(name = "longitudSiPlouAprox")       private Integer longitudSiPlouAprox;
    @Column(name = "municipi")                  private String municipi;
    @Column(name = "poblacioCurta")             private String poblacioCurta;
    @Column(name = "poblacioMitjana")           private String poblacioMitjana;
    @Column(name = "poblacioAmpliada")          private String poblacioAmpliada;
    @Column(name = "comarca")                   private String comarca;
    @Column(name = "territori")                 private String territori;
    @Column(name = "cobla1")                    private String cobla1;
    @Column(name = "cobla2")                    private String cobla2;
    @Column(name = "cobla3")                    private String cobla3;
    @Column(name = "cobla4")                    private String cobla4;
    @Column(name = "cobla5")                    private String cobla5;
    @Column(name = "cobla6")                    private String cobla6;
    @Column(name = "cobla7")                    private String cobla7;
    @Column(name = "musicsNoCobla")             private String musicsNoCobla;
    @Column(name = "musicsNoCobla2")            private String musicsNoCobla2;
    @Column(name = "totsElsInterprets")         private String totsElsInterprets;

}
