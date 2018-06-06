package es.tdmsl.obd.presentacion;

import javax.swing.*;

import es.tdmsl.obd.dominio.ControladorDominioConexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;

public class ControladorPrincipal {
    VistaPrincipal VP;
    VistaConfiguracion VC;
    ControladorDominioConexion contDomCon;
    ControladorPSerie cP;
    ControladorProtocolo cProt;
    ControladorMediciones cMed;
    ControladorErrores cErr;
    JPanel panel;
    JLabel labelImagen;
    JEditorPane jEditorPane;
    // JEditorPane jEditorPaneNotas;
    JScrollPane jScrollPane;
    boolean existe_panel = false;
    boolean existe_label = true;
    boolean existe_jEditorPane;
    boolean existe_notas = false;

    public ControladorPrincipal() {
        VP = new VistaPrincipal();
        VC = new VistaConfiguracion();
        contDomCon = new ControladorDominioConexion(VP.getJTextArea());
        cP = new ControladorPSerie(contDomCon);
        cProt = new ControladorProtocolo(contDomCon);
        cMed = new ControladorMediciones(contDomCon);
        cErr = new ControladorErrores(contDomCon, cMed);
        labelImagen = new JLabel();
        labelImagen.setSize(791, 677);
        try {
            File miDir = new File(".");
            System.out.println(miDir.getCanonicalPath());
            jEditorPane = new JEditorPane(new URL("file:///" + miDir.getCanonicalPath() + "/src/es/tdmsl/obd/html/principal.html"));
            jEditorPane.setEditable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jScrollPane = new JScrollPane(jEditorPane);
        jScrollPane.setSize(791, 677);
        //labelImagen.setSize(791, 677);
        //labelImagen.setIcon (new ImageIcon("VisualOBD4_1.jpg"));
        VP.getJContentPane().add(jScrollPane, null);
        existe_jEditorPane = true;
        VP.initialize();
    }

    public void inicializar() {

        VP.getJButtonConectar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                //if (VC.getjRadioButtonPSerie().isSelected() == true) {
                //System.out.println("jRadioButtonPSerie.isSelected()");

                contDomCon.establecerConexion(
                        cP.cojerDatosConexion().getVelocidad(),
                        cP.cojerDatosConexion().getDataBits(),
                        cP.cojerDatosConexion().getStopBits(),
                        cP.cojerDatosConexion().getParidad(),
                        cP.cojerDatosConexion().getNombrePuerto(),
                        cProt.fijar_protocolo(),
                        cP.cojerDatosConexion().getTipoConexion()
                );

                cMed.pararHilo = false;
                cMed.inicializacion = true;
                cMed.conectado = true;
            }
        });
        VP.getJButtonDesconectar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cMed.pararHilo = true;
                cMed.conectado = false;
                if (cMed.lectura == true) {
                    while (cMed.indica_hilo_destruido == false) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException des) {
                            des.printStackTrace();
                        }
                    }
                    contDomCon.desconectar();
                } else {
                    contDomCon.desconectar();
                }
                cMed.lectura = false;
            }
        });
        VP.getJMenuItemPSerie().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                }

                panel = cP.retornaPanel();
                VP.getJContentPane().add(panel, null);
                existe_panel = true;
                VP.initialize();
            }
        });
        VP.getJMenuItemProtocolo().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                }
                panel = cProt.devuelve_panel();
                VP.getJContentPane().add(panel, null);
                existe_panel = true;
                VP.initialize();
            }
        });
        VP.getJMenuItemErrores().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                }
                panel = cErr.devuelve_panel();
                VP.getJContentPane().add(panel, null);
                existe_panel = true;
                VP.initialize();
            }
        });
        VP.getJMenuItemLectura().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                }
                panel = cMed.devuelve_panel();
                VP.getJContentPane().add(panel, null);
                existe_panel = true;
                VP.initialize();
            }
        });
        VP.getJMenuItemPresentacion().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                    }
                    existe_notas = false;
                }
                //labelImagen.setSize(791, 677);
                //labelImagen.setIcon (new ImageIcon("VisualOBD4_1.jpg"));
                try {
                    File miDir = new File(".");
                    System.out.println(miDir.getCanonicalPath());
                    jEditorPane = new JEditorPane(new URL("file:///" + miDir.getCanonicalPath() + "/src/es/tdmsl/obd/html/principal.html"));
                    jEditorPane.setEditable(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setSize(791, 677);
                VP.getJContentPane().add(jScrollPane, null);
                existe_jEditorPane = true;
                VP.initialize();
            }
        });

        VP.getJMenuItemExit().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (existe_notas == true) {
                    guardaNotas();
                }
                System.exit(0);
            }
        });
        VP.getJButtonEnvioManual().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contDomCon.permiteEnvioManual(true);
                contDomCon.enviar(VP.getJTextFieldEnvioManual().getText());
                VP.getJTextFieldEnvioManual().setText("");
            }
        });
        VP.getJTextFieldEnvioManual().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    VP.getJButtonEnvioManual().doClick();
                }
            }
        });
        VP.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        VP.getJMenuItemAbout().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                    }
                    existe_notas = false;
                }

                System.out.println("file:///" + System.getProperty("user.dir") + "/src/es/tdmsl/obd/html/informacion.html");
                try {
                    jEditorPane = new JEditorPane(new URL("file:///" + System.getProperty("user.dir") + "/src/es/tdmsl/obd/html/informacion.html"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jEditorPane.setEditable(false);
                jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setSize(791, 677);
                VP.getJContentPane().add(jScrollPane, null);
                existe_jEditorPane = true;
                VP.initialize();
            }
        });

        VP.getJMenuComandosAT().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                        existe_notas = false;
                    }
                }
                try {
                    jEditorPane = new JEditorPane(new URL("https://es.wikibooks.org/wiki/El_OBDII_Completo/Herramientas_de_Diagnostico/ELM327"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jEditorPane.setEditable(false);
                jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setSize(791, 677);
                VP.getJContentPane().add(jScrollPane, null);
                existe_jEditorPane = true;
                VP.initialize();

            }
        });

        VP.getJMenuPids().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                        existe_notas = false;
                    }
                }
                try {
                    jEditorPane = new JEditorPane(new URL("https://es.wikipedia.org/wiki/OBD-II_PID"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jEditorPane.setEditable(false);
                jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setSize(791, 677);
                VP.getJContentPane().add(jScrollPane, null);
                existe_jEditorPane = true;
                VP.initialize();

            }
        });

        VP.getJMenuComandosAT().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                        existe_notas = false;
                    }
                }
                try {
                    jEditorPane = new JEditorPane(new URL("https://www.sparkfun.com/datasheets/Widgets/ELM327_AT_Commands.pdf"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jEditorPane.setEditable(false);
                jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setSize(791, 677);
                VP.getJContentPane().add(jScrollPane, null);
                existe_jEditorPane = true;
                VP.initialize();

            }
        });

        VP.getJMenuCodigosAscII().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                        existe_notas = false;
                    }
                }
                try {
                    jEditorPane = new JEditorPane(new URL("http://ascii.cl/es/"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jEditorPane.setEditable(false);
                jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setSize(791, 677);
                VP.getJContentPane().add(jScrollPane, null);
                existe_jEditorPane = true;
                VP.initialize();

            }
        });

        VP.getJMenuImagenes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {
                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                        existe_notas = false;
                    }
                }
                try {
                    jEditorPane = new JEditorPane(new URL(""));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jEditorPane.setEditable(false);
                jScrollPane = new JScrollPane(jEditorPane);
                jScrollPane.setSize(791, 677);
                VP.getJContentPane().add(jScrollPane, null);
                existe_jEditorPane = true;
                VP.initialize();

            }
        });

        VP.getJMenuNotas().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(e);
                if (existe_panel == true) {
                    VP.getJContentPane().remove(panel);
                }
                if (existe_label == true) {
                    VP.getJContentPane().remove(labelImagen);
                }
                if (existe_jEditorPane == true) {

                    VP.getJContentPane().remove(jScrollPane);
                    if (existe_notas == true) {
                        guardaNotas();
                    }
                }
                jEditorPane = new JEditorPane();
                cargaNotas();
                existe_jEditorPane = true;
                VP.initialize();
            }
        });

    }//fin inicializar

    private void cargaNotas() {
        existe_notas = true;
        String linea;
        StringBuffer notas = new StringBuffer();
        FileReader f = null;
        try {
            f = new FileReader(System.getProperty("user.dir") + "/src/es/tdmsl/obd/html/notas.txt");
            BufferedReader b = new BufferedReader(f);

            while ((linea = b.readLine()) != null) {
                // System.out.println(notas);
                notas.append(linea + "\r\n");
            }
            jEditorPane.setText(notas.toString());
            jScrollPane = new JScrollPane(jEditorPane);
            jScrollPane.setSize(791, 677);
            VP.getJContentPane().add(jScrollPane, null);
            //System.out.println("cargando notas "+notas.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void guardaNotas() {
        String ruta = System.getProperty("user.dir") + "/src/es/tdmsl/obd/html/notas.txt";

        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {

            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                //bw.write("El fichero de texto ya estaba creado.");
                bw.write(jEditorPane.getText());
                bw.close();
                //System.out.println("guardando notas en "+jEditorPane.getText()+" en "+ruta);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else {

            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("Acabo de crear el fichero de texto.");
                bw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        ControladorPrincipal conPrin = new ControladorPrincipal();
        conPrin.inicializar();
    }
}
