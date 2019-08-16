package com.mycompany.fileupload_1;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import javax.swing.table.DefaultTableModel;

public class UI extends javax.swing.JFrame {

    ArrayList<Integer> Ctc = new ArrayList<Integer>();
    ArrayList<Integer> Cnc = new ArrayList<Integer>();
    ArrayList<Integer> lineNumber = new ArrayList<Integer>();
    ArrayList<String> programStatement = new ArrayList<String>();
    int maxCases = 0;

    public UI() {
        initComponents();
        setExtendedState(UI.MAXIMIZED_BOTH);
        ButtonGroup group = new ButtonGroup();
        group.add(btnjava);
        group.add(btnc);
        group.add(btnClear);
        btnClear.setVisible(false);

    }

    public int getSwitchCaseCount() {

        int current_maxCases = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());

        // Read through file and find words
        while (fileInput.hasNextLine()) {
            String scannedline = fileInput.nextLine();

            if (scannedline.matches("(\\s+)case(.*)")) {
                current_maxCases++;
                // System.out.println(scannedline);
                // update max if required  
                if (current_maxCases > maxCases) {
                    maxCases = current_maxCases;
                }
            } else if (scannedline.matches("(\\s+)case(.*)")) {
                if (current_maxCases > 0) {
                    current_maxCases--;
                    System.out.println(scannedline);
                }
            }
        }

        return maxCases;

    }

    public ArrayList<Integer> calculateCtc() {
        int CtcCounter = 0;
        int lineNumber = 0;
        int caseCount = 0;

        Scanner fileInput = new Scanner(uploadedContent.getText());

        // Read through file and find words
        while (fileInput.hasNextLine()) {

            lineNumber = lineNumber + 1;

            String scannedline = fileInput.nextLine();

            //******************************IF uploded source code in JAVA language
            if (btnjava.isSelected()) {
                if (scannedline.contains("System.out.println") || scannedline.startsWith("//") || (scannedline.startsWith("/*")) || scannedline.startsWith("*/")) {
                    CtcCounter = 0;
                    //continue;
                } else if (scannedline.contains("if")) {
                    CtcCounter = CtcCounter + 1;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 1;
                    }
                } else if (scannedline.contains("for")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("while")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("do")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("catch")) {
                    CtcCounter = CtcCounter + 1;
                } else if (scannedline.matches("(\\s+)switch(.*)")) {
                    getSwitchCaseCount();
                    CtcCounter = maxCases;
                }

                Ctc.add(CtcCounter);

                CtcCounter = 0;
                maxCases = 0;

            } //******************************IF uploded source code in C++ language
            else if (btnc.isSelected()) {

                if (scannedline.contains("cout <<") || scannedline.startsWith("//") || (scannedline.startsWith("/*")) || scannedline.startsWith("*/")) {
                    CtcCounter = 0;
                    //continue;
                } else if (scannedline.contains("if")) {
                    CtcCounter = CtcCounter + 1;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 1;
                    }
                } else if (scannedline.contains("for")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("while")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("do")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("catch")) {
                    CtcCounter = CtcCounter + 1;
                } else if (scannedline.matches("(\\s+)switch(.*)")) {
                    getSwitchCaseCount();
                    CtcCounter = maxCases;
                }

                Ctc.add(CtcCounter);

                CtcCounter = 0;
                maxCases = 0;
            }
        }

        noOfLines.setText("No of Lines : " + lineNumber);

        return Ctc;

    }

    public ArrayList<Integer> calculateCnc() {

        int current_max = 0; // current count  
        int CncCounter = 0; // overall maximum count
        int lineNumber = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());

        // Read through file and find words
        while (fileInput.hasNextLine()) {
            lineNumber = lineNumber + 1;
            String scannedline = fileInput.nextLine();

            //******************************IF uploded source code in JAVA language
            if (btnjava.isSelected()) {
                if (scannedline.matches("(\\s+)if(.*)") || scannedline.matches("(\\s+)else(.*)") || scannedline.matches("(\\s+)for(.*)")) {
                    current_max++;
                    ////////System.out.println(scannedline);
                    // update max if required  
                    if (current_max > CncCounter) {
                        CncCounter = current_max;
                    }
                } else if (scannedline.contains("}")) {
                    if (current_max > 0) {
                        current_max--;
                        CncCounter = current_max;
                    }
                }

                Cnc.add(CncCounter);
            }
            //******************************IF uploded source code in C++ language
            if (btnc.isSelected()) {
                if (scannedline.matches("(\\s+)if(.*)") || scannedline.matches("(\\s+)else(.*)") || scannedline.matches("(\\s+)for(.*)")) {
                    current_max++;
                    System.out.println(scannedline);
                    // update max if required  
                    if (current_max > CncCounter) {
                        CncCounter = current_max;
                    }
                } else if (scannedline.contains("}")) {
                    if (current_max > 0) {
                        current_max--;
                        CncCounter = current_max;
                    }
                }

                Cnc.add(CncCounter);
            }

        }
        noOfLines.setText("No of Lines : " + lineNumber);
        return Cnc;
    }

    public ArrayList<String> readFile() {
        int lineNo = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());
        // Read through file and find words
        while (fileInput.hasNextLine()) {
            lineNo = lineNo + 1;
            String scannedline = fileInput.nextLine();
            programStatement.add(scannedline);
            lineNumber.add(lineNo);
        }
        return programStatement;
    }

    public void viewResult() {
        readFile();
        DefaultTableModel model = (DefaultTableModel) result.getModel();
        Object[] CtcObjs = Ctc.toArray();
        Object[] CncObjs = Cnc.toArray();
        Object[] programStatementObjs = programStatement.toArray();
        Object[] lineNumberObjs = lineNumber.toArray();
        model.addColumn("Line No", lineNumberObjs);
        model.addColumn("Program Statements", programStatementObjs);
        model.addColumn("Ctc", CtcObjs);
        model.addColumn("Cnc", CncObjs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btnAttchFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        uploadedContent = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        path = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnjava = new javax.swing.JRadioButton();
        btnc = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        noOfLines = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        testArrayList = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        result = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnCi2 = new javax.swing.JButton();
        btnCs = new javax.swing.JButton();
        btnCtc = new javax.swing.JButton();
        btnCnc = new javax.swing.JButton();
        btnCi3 = new javax.swing.JButton();
        btnCi1 = new javax.swing.JButton();
        btnCi = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        btnClear = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("jMenu3");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        btnAttchFile.setBackground(new java.awt.Color(255, 255, 0));
        btnAttchFile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAttchFile.setText("Attach File");
        btnAttchFile.setBorder(null);
        btnAttchFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttchFileActionPerformed(evt);
            }
        });

        uploadedContent.setEditable(false);
        uploadedContent.setColumns(20);
        uploadedContent.setRows(5);
        jScrollPane1.setViewportView(uploadedContent);

        jLabel2.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel2.setText("Calculate Complexity");

        path.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Inserted Source Code :");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setText("Calculated Complexity :");

        btnjava.setBackground(new java.awt.Color(204, 255, 255));
        btnjava.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnjava.setText("JAVA");

        btnc.setBackground(new java.awt.Color(204, 255, 255));
        btnc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnc.setText("C++");

        jLabel4.setFont(new java.awt.Font("Segoe Print", 2, 36)); // NOI18N
        jLabel4.setText("ABC Solutions");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Choose Language : ");

        noOfLines.setEditable(false);

        btnReset.setBackground(new java.awt.Color(102, 102, 102));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.setBorder(null);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        testArrayList.setBackground(new java.awt.Color(0, 204, 51));
        testArrayList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        testArrayList.setForeground(new java.awt.Color(255, 255, 255));
        testArrayList.setText("Display Output");
        testArrayList.setBorder(null);
        testArrayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testArrayListActionPerformed(evt);
            }
        });

        result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(result);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        btnCi2.setBackground(new java.awt.Color(0, 51, 204));
        btnCi2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCi2.setForeground(new java.awt.Color(255, 255, 255));
        btnCi2.setText("Cps");
        btnCi2.setBorder(null);
        btnCi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCi2ActionPerformed(evt);
            }
        });

        btnCs.setBackground(new java.awt.Color(0, 51, 204));
        btnCs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCs.setForeground(new java.awt.Color(255, 255, 255));
        btnCs.setText("Cs");
        btnCs.setBorder(null);
        btnCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsActionPerformed(evt);
            }
        });

        btnCtc.setBackground(new java.awt.Color(0, 51, 204));
        btnCtc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCtc.setForeground(new java.awt.Color(255, 255, 255));
        btnCtc.setText("Ctc");
        btnCtc.setBorder(null);
        btnCtc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCtcActionPerformed(evt);
            }
        });

        btnCnc.setBackground(new java.awt.Color(0, 51, 204));
        btnCnc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCnc.setForeground(new java.awt.Color(255, 255, 255));
        btnCnc.setText("Cnc");
        btnCnc.setBorder(null);
        btnCnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCncActionPerformed(evt);
            }
        });

        btnCi3.setBackground(new java.awt.Color(0, 51, 204));
        btnCi3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCi3.setForeground(new java.awt.Color(255, 255, 255));
        btnCi3.setText("Cr");
        btnCi3.setBorder(null);
        btnCi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCi3ActionPerformed(evt);
            }
        });

        btnCi1.setBackground(new java.awt.Color(0, 51, 204));
        btnCi1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCi1.setForeground(new java.awt.Color(255, 255, 255));
        btnCi1.setText("TW");
        btnCi1.setBorder(null);
        btnCi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCi1ActionPerformed(evt);
            }
        });

        btnCi.setBackground(new java.awt.Color(0, 51, 204));
        btnCi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCi.setForeground(new java.awt.Color(255, 255, 255));
        btnCi.setText("Ci");
        btnCi.setBorder(null);
        btnCi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCtc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCnc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCi1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCi2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCi3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCtc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCnc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCi1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCi2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCi3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCs, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WordTest/logoABC.gif"))); // NOI18N

        btnClear.setBackground(new java.awt.Color(204, 255, 255));
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(noOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClear))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnjava)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnc)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAttchFile, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(testArrayList, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 238, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(logo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAttchFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnjava)
                    .addComponent(btnc)
                    .addComponent(jLabel5)
                    .addComponent(testArrayList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(noOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAttchFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttchFileActionPerformed

        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter docFilter = new FileNameExtensionFilter("Word Files", "docx");
            chooser.setFileFilter(docFilter);
            int value = chooser.showOpenDialog(null);

            if (value == JFileChooser.APPROVE_OPTION) {
                XWPFDocument doc = new XWPFDocument(new FileInputStream(chooser.getSelectedFile()));
                XWPFWordExtractor extract = new XWPFWordExtractor(doc);
                uploadedContent.setText(extract.getText());
                File f = chooser.getSelectedFile();
                String fileName = f.getAbsolutePath();
                path.setText(fileName);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_btnAttchFileActionPerformed

    private void btnCtcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCtcActionPerformed

        calculateCtc();

    }//GEN-LAST:event_btnCtcActionPerformed

    private void btnCncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCncActionPerformed

        calculateCnc();

    }//GEN-LAST:event_btnCncActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        path.setText(null);
        noOfLines.setText(null);
        uploadedContent.setText(null);
        DefaultTableModel ClearModel = (DefaultTableModel) result.getModel();
        ClearModel.setRowCount(0);
        btnClear.setSelected(true);

    }//GEN-LAST:event_btnResetActionPerformed

    private void testArrayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testArrayListActionPerformed

        viewResult();

    }//GEN-LAST:event_testArrayListActionPerformed

    private void btnCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsActionPerformed

    }//GEN-LAST:event_btnCsActionPerformed

    private void btnCiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCiActionPerformed

    private void btnCi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCi1ActionPerformed

    private void btnCi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCi2ActionPerformed

    private void btnCi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCi3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCi3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                /*
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                 */
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttchFile;
    private javax.swing.JButton btnCi;
    private javax.swing.JButton btnCi1;
    private javax.swing.JButton btnCi2;
    private javax.swing.JButton btnCi3;
    private javax.swing.JRadioButton btnClear;
    private javax.swing.JButton btnCnc;
    private javax.swing.JButton btnCs;
    private javax.swing.JButton btnCtc;
    private javax.swing.JButton btnReset;
    private javax.swing.JRadioButton btnc;
    private javax.swing.JRadioButton btnjava;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField noOfLines;
    private javax.swing.JTextField path;
    private javax.swing.JTable result;
    private javax.swing.JButton testArrayList;
    private javax.swing.JTextArea uploadedContent;
    // End of variables declaration//GEN-END:variables
}
