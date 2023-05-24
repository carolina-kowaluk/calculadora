
public class Calculadora {
    Pilha pilha = new Pilha();
    int count=0;
    int countMax=0;
    /**
     * verifica a sintaxe da expressão 
     * @param s lista de string
     * @return se a sintaxe esta correta, senão retorna o erro de sintaxe
     * complexidade do algoritmo: O(n^2)
     */
    public String verificaExpressao(String s[]) {
        pilha.clear();
        for(int i=0; i<s.length; i++) {
            String str = s[i];
            //se str for um "abre" adiciona na pilha
            if (str.equals("{") || str.equals("[") || str.equals("("))
                pilha.push(str);
            //se str for um "fecha" 
            else if(str.equals("}") || str.equals("]") || str.equals(")")){ 
                //verifica se apilha esta vazia
                if (pilha.isEmpty())  
                    return "Pilha esta vazia";

                String aux = pilha.pop(); //armazena e remove o topo da pilha
                //se aux for um "abre" e str NÃO for um "fecha", retorna o erro
                if (aux.equals("{") && !str.equals("}")) 
                    return "Erro de sintaxe: "+str+" no lugar de }";
                if (aux.equals("[") && !str.equals("]"))
                    return "Erro de sintaxe: "+str+" no lugar de ]";
                if (aux.equals("(") && !str.equals(")"))
                    return "Erro de sintaxe: "+str+" no lugar de )";
            }            
    }
    if (pilha.isEmpty()) //se a pilha esta vazia
        return "Sintaxe correta";
    else //senão retorna erro
        return "Erro na expressão"; 
    }
    /**
     * se a sintaxe esta correta, faz a verificação da ordem: operando1, operador, operando2 
     * e calcula o resultado da expressão
     * @param s lista de String
     * @return o resultado da expressão
     * complexidade do algoritmo: O(n^2)
     */       
    public Double calcula(String s[]) {
        String aux;
        String operador;
        double op1=0;
        double op2=0;
        double resultado=0; 
        String mensagem = verificaExpressao(s);
        count = 0;
        countMax = 0;
        //se a sintaxe do verificaExpressão esta correta retorna resultado
        if(mensagem.equals("Sintaxe correta")) {
            pilha.clear(); 
            for(int i=0; i<s.length; i++) { 
                //se a String não for um "fecha", adiciona na pilha
                if(!s[i].equals("}") && !s[i].equals("]") && !s[i].equals(")")) {
                    pilha.push(s[i]);
                    count++; 
                    if(count>countMax) 
                        countMax=count;
                } 
                //senão faz o cálculo
                else {  
                    try{ //armazena e remove o segundo operando no topo da pilha
                        aux = pilha.pop();
                        count--;
                        op2 = Double.parseDouble(aux); //transforma o aux em double
                    }
                    catch (NumberFormatException e){ //gera erro caso falte o operando 2
                        System.out.println("Erro de sintaxe: Falta operando 2!");
                        countMax=0;
                        return null;
                    }
                    //armazena e remove o operador no topo da pilha
                    operador = pilha.pop();
                    count--;
                    //gera erro caso falte o operador
                    if(!operador.equals("+") && !operador.equals("-") && !operador.equals("*") && !operador.equals("/") && !operador.equals("^")) {
                        System.out.println("Erro de sintaxe: Falta operador!");
                        countMax=0;
                        return null;
                    }  
                    try { //armazena e remove o primeiro operando no topo da pilha
                        aux = pilha.pop();
                        count--;
                        op1 = Double.parseDouble(aux); //transforma o aux em double
                    }
                    catch(NumberFormatException e) { //gera erro caso falte o operando 1
                        System.out.println("Erro na sintaxe: Falta operando 1!");
                        countMax=0;
                        return null;
                    }
                    //remove o "abre" da expressão 
                    aux = pilha.pop();
                    count--;
                    //realiza a operação e adiciona resultado na pilha
                    resultado = operacao(op1, op2, operador);
                    String result = Double.toString(resultado); //transforma resultado em string
                    pilha.push(result);
                    count++;
                }
            }
            System.out.println(mensagem);
            return resultado; 
        }
        else { //senão retorna null
         System.out.println(mensagem);
         return null;
        }
    }
    /**
     * Realiza as operações de soma, subtração, multiplicação, divisão e potência entre dois operandos
     * @param op1 operando 1
     * @param op2 operando 2
     * @param operador
     * @return
     * Complexidade do algoritmo: O(1)
     */
    public double operacao(double op1, double op2, String operador) {
        if(operador.equals("+")) { //se "+" faz operação de soma
            return op1 + op2;
        } 
        if(operador.equals("-")) { //se "-" faz operação de subtração
            return op1 - op2;
        } 
        if(operador.equals("*")) { //se "*" faz operação de multiplicação
            return op1 * op2;
        }   
        if(operador.equals("/")) { //se "/" faz operação de divisão
            return op1 / op2;
        }
        else { //senão faz operação de potência
            int intOp1 = (int) Math.round(op1);
            int intOp2 = (int) Math.round(op2);
            return intOp1 ^ intOp2;
        }
    }                      
}