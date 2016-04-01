/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

//import antlr4.programLexer;
//import antlr4.programParser;
import antlr.sqlLexer;
import antlr.sqlParser;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author Pablo
 */
public class ANTGui extends javax.swing.JFrame {
    
    private File inputFile;
    private File inputGrammar;
    public static String[] ruleNames;
    private sqlParser parser;
    private sqlParser.Sql2003ParserContext contexto;
    public static boolean selected = false;
//    
    /**
     * Creates new form ANTGui
     */
    public ANTGui() {
        initComponents();
        toolbar();
        lines();
        
        /*DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});
         model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});*/
       
        
    }
    
    private void lines(){
   
    TextLineNumber tln = new TextLineNumber(this.jTextArea2);
    this.jScrollPane2.setRowHeaderView(tln);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Input", jPanel2);

        jToolBar1.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar1.setForeground(new java.awt.Color(204, 204, 204));
        jToolBar1.setRollover(true);
        jToolBar1.setToolTipText("ToolBar");

        jScrollPane4.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Log", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre Var", "Tipo", "Ambito", "Associated"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1039, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Tabla de Símbolos", jPanel5);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1051, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Gramática", jPanel4);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Cargar gramática");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Cargar archivo de entrada");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Acciones");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void toolbar(){
       
        
        //************************ Actions ************************
        Action newFileButton = new AbstractAction("Nuevo",new ImageIcon("src/resources/new-git.png")) {
            public void actionPerformed(ActionEvent e) {
               nuevoArchivo();
            }
        };
        
        
        Action openFileAction = new AbstractAction("Abrir",new ImageIcon("src/resources/open-git.png")) {
            public void actionPerformed(ActionEvent e) {
               abrirArchivo();
            }
        };
        
        
        Action saveAction = new AbstractAction("Guardar",new ImageIcon("src/resources/update-git.png")) {
            public void actionPerformed(ActionEvent e) {
               guardarArchivo();
            }
        };
        
        
          Action compileAction = new AbstractAction("Compilar", new ImageIcon("src/resources/compile.png")) {
            public void actionPerformed(ActionEvent e) {
                compilar();
            }
        };
          
             Action showTree = new AbstractAction("Arbol", new ImageIcon("src/resources/tree-git.png")) {
            public void actionPerformed(ActionEvent e) {
                mostrarArbol();
            }
        };
             
        Action debugAction = new AbstractAction("Debug", new ImageIcon("src/resources/bug-git.png")) {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = !selected;
                if (selected){
                    jLabel1.setText("Debug activado");
                }
                else{
                    jLabel1.setText("Debug desactivado");
                }
               
            }
        };
          
        jMenu2.setMnemonic('R');
        JMenuItem item = jMenu1.add(compileAction);
            jMenu2.add(item);
            item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
            item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        jMenu2.setMnemonic('O');
        item = jMenu2.add(openFileAction);
            jMenu2.add(item);
            item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
            item.setAccelerator((javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,ActionEvent.CTRL_MASK)));
        jMenu2.setMnemonic('T');
        item = jMenu2.add(showTree);
            jMenu2.add(item);
           item.setAccelerator((javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T,ActionEvent.CTRL_MASK)));
        jMenu2.setMnemonic('S');
        item = jMenu2.add(saveAction);
            jMenu2.add(item);
           item.setAccelerator((javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,ActionEvent.CTRL_MASK)));
        
            JButton btnNew = this.jToolBar1.add(newFileButton);
        btnNew.setToolTipText("Nuevo archivo");
        
        this.jToolBar1.add(openFileAction).setToolTipText("Abrir archivo");
        this.jToolBar1.add(saveAction).setToolTipText("Guardar");
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
        this.jToolBar1.addSeparator();
       
        this.jToolBar1.add(showTree).setToolTipText("Mostrar Arbol");
        this.jToolBar1.add(compileAction).setToolTipText("COMPILAR");
       
        
        
        this.jToolBar1.add((debugAction));
        
        
        jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(150);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(45);
        
    }
    
    public void nuevoArchivo(){
        
    }
    
    public void guardarArchivo(){
       crearArchivo(this.jTextArea2.getText(),inputFile);
    }
    
    public void abrirArchivo(){
               JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            inputFile = fc.getSelectedFile();
            //This is where a real application would open the file.
            String dir = inputFile.toString();
            String input="";
            try{
            BufferedReader br = new BufferedReader(new FileReader(inputFile.getAbsoluteFile()));
            String sCurrentLine;
            
               
               while ((sCurrentLine = br.readLine()) != null) {
                    
                    input+=sCurrentLine+"\n";
                
                }
            }catch(Exception e){}
            
            this.jTextArea2.setText(input);
            
           
        }
        else{
            System.out.println("Error al abrir el archivo");
        }
       
    }
    
    public void compilar(){
          try{
              
            jTextArea3.setText("");
            String in="";
            try{
                in = leerArchivo(inputFile);
            }catch(Exception e){}
            if (inputFile == null){
                in = this.jTextArea2.getText();
            }
            CharStream cs =  new ANTLRInputStream(in);

           sqlLexer lexer = new sqlLexer(cs);
            //lexer.removeErrorListeners();
            //lexer.addErrorListener(DescriptiveErrorListener.INSTANCE);
            CommonTokenStream tokens = new CommonTokenStream( lexer);
            parser = new sqlParser(tokens);
            contexto = parser.sql2003Parser();
            ParseTree tree = contexto;

            

            //parser.removeErrorListeners();
            //parser.addErrorListener(DescriptiveErrorListener.INSTANCE);

            // Specify our entry point
            
            ruleNames = parser.getRuleNames();
            

            int errorsCount = parser.getNumberOfSyntaxErrors();
            System.out.println(errorsCount);
            if(errorsCount == 0){
                System.out.println("Parseo Exitoso");

               JOptionPane.showMessageDialog(this, "¡Parseo Exitoso!");
                Visitor vistor = new Visitor();
                vistor.visit(tree);

            }
            else{
                 JOptionPane.showMessageDialog(this, "¡Parseo Fallido!");
            }

        } catch (RecognitionException e) {
            System.out.println("ERROR");

        }
       // insertarTablaIDE();
         
    }
    
    
//    public void insertarTablaIDE(){
//        
//       
//        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
//        model.setRowCount(0);
//        for (Map.Entry<Integer, Symbol> entry : Visitor.tablaSimbolos.getTabla().entrySet()) {
//            int key = entry.getKey();
//            Symbol simbolo = entry.getValue();
//            String paramsString="";
//            String methodString ="";
//            
//            //agrega los tipos Method Type a un string y sus parametros para agregarlo a la tabla
//            
//            if (simbolo.getTipo().getClass().getName().equals("compiler.MethodType")){
//                ArrayList<Symbol> params = ((MethodType)simbolo.getTipo()).getParameters();
//               
//                for (int i = 0;i<params.size();i++){
//                    paramsString +="id: "+ (params.get(i).getId())+" -> "+ ((compiler.Type)params.get(i).getTipo()).getNombreVariable()+ "  ";
//                }
//                methodString = " method";
//            }
//            //agrega los tipos Struct Type a un string y sus miembros a un string
//            //para agregarlo a la tabla.
//            if (simbolo.getTipo().getClass().getName().equals("compiler.StructType")){
//                ArrayList<Symbol> members = ((StructType)simbolo.getTipo()).getMembers();
//               
//                for (int i = 0;i<members.size();i++){
//                    paramsString +="id: "+ (members.get(i).getId())+" -> "+ ((compiler.Type)members.get(i).getTipo()).getNombreVariable()+ "  ";
//                }
//                methodString = " ";
//            }
//            //agrega símbolo a a la tabla del IDE.
//            model.addRow(new Object[]{key, ((compiler.Type)simbolo.getTipo()).getNombreVariable(),
//               ((compiler.Type)simbolo.getTipo()).getModLiteralTipo()+ methodString ,
//                simbolo.getAmbito(),
//                paramsString});
//        }
//        
//        
//    }
    
    public void mostrarArbol(){
        Trees.inspect(contexto, parser);
        
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            inputGrammar = fc.getSelectedFile();
            //This is where a real application would open the file.
            String dir = inputGrammar.toString();
            String input="";
            try{
            BufferedReader br = new BufferedReader(new FileReader(inputGrammar.getAbsoluteFile()));
            String sCurrentLine;
            
               
               while ((sCurrentLine = br.readLine()) != null) {
                    
                    input+=sCurrentLine+"\n";
                
                }
            }catch(Exception e){}
            
            //this.jTextArea1.setText(input);
           
        }
        else{
            System.out.println("Error al abrir el archivo");
        }
       
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            inputFile = fc.getSelectedFile();
            //This is where a real application would open the file.
            String dir = inputFile.toString();
            String input="";
            try{
            BufferedReader br = new BufferedReader(new FileReader(inputFile.getAbsoluteFile()));
            String sCurrentLine;
            
               
               while ((sCurrentLine = br.readLine()) != null) {
                    
                    input+=sCurrentLine+"\n";
                
                }
            }catch(Exception e){}
            
            this.jTextArea2.setText(input);
            
           
        }
        else{
            System.out.println("Error al abrir el archivo");
        }
       
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    
    public static String leerArchivo(File inputFile){
      
        int contador=0;
        int tamaño=0;
        String input=" ";
        BufferedReader br = null;
 
        try {

                String sCurrentLine;
                
                br = new BufferedReader(new FileReader(inputFile.getAbsoluteFile()));

               
               
               while ((sCurrentLine = br.readLine()) != null) {
                    
                    input+=sCurrentLine+"\n";
                
                }
             
                
        input+="\n";
              
        return input;
        } catch (IOException e) {
               
        } finally {
                try {
                        if (br != null)br.close();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
        }
        return null;
        
    }
    
     public void crearArchivo(String output,File archivo){
        try {
            
             
               
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
               
                bw.write(output);
            

                bw.close();

                System.out.println("Se ha guardado el archivo exitosamente");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
     public void debug(String str, boolean debug){
         if (debug){
             System.out.println(str);
         }
     }
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ANTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ANTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ANTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ANTGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ANTGui().setVisible(true);
            }
        });
        
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea2;
    public static javax.swing.JTextPane jTextArea3;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
