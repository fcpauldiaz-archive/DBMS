/**
* Universidad Del Valle de Guatemala
* 26-mar-2016
* Pablo Díaz 13203
*/

package dbms;

/**
 *
 * @author Pablo
 */
public class TuplaCheck {
    
    private String op1;
    private String op2;
    private String operador;

    public TuplaCheck(String op1, String op2, String operador) {
        this.op1 = op1;
        this.op2 = op2;
        this.operador = operador;
    }

    public TuplaCheck() {
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    @Override
    public String toString() {
        return "TuplaCheck{" + "op1=" + op1 + ", op2=" + op2 + ", operador=" + operador + '}';
    }
    
    
    

}
