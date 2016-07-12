package gamemine;

/**
 *
 * @author Rubén Manzano
 */
public class Initialize implements MineSweeper{
    private char[][] game;
    private int[][] aux;
    private int c,f;
    
    public void setMineField(String mineField) throws IllegalArgumentException{
        if(mineField==null||mineField.equalsIgnoreCase("")){
            throw new IllegalArgumentException();
        }
        //Separo el string en partes
        String[]parts = mineField.split("\\n");
        //compruebo que el tamaño de las partes sea igual
        for(int i=0;i<parts.length-1;i++){
            if(parts[i].length()!=parts[i+1].length())
                throw new IllegalArgumentException();
        }
        //creo la matriz
        game=new char[parts.length][parts[0].length()];
        //compruebo que en la cadena solo haya . o *
        for(int i=0;i<parts.length;i++){
            for(int j=0;j<parts[i].length();j++){
                //si es caracter válido lo inserto en la matriz
                if(parts[i].charAt(j)=='.'||parts[i].charAt(j)=='*'){
                    game[i][j]=parts[i].charAt(j);
                }else
                    throw new IllegalArgumentException();
                    
            }
        }
    }
    
    public String getHintField() throws IllegalStateException{
        //compruebo si se ha llamado a setMineField ya que si no 
        //se ha llamado game no estará creado y lanzará excepción
        try{
            c = game[0].length; //colums
            f = game.length;    //files
        }catch(Exception e){
            throw new IllegalArgumentException();
        }
        aux=new int[f][c];
//        aux=MatrizToInt(aux);
//        aux=Convert(aux);
//        return MatriztoString(aux);
        return MatriztoString(Convert(MatrizToInt(aux)));
    }
    
    
    private int[][] MatrizToInt(int aux[][]){
        //si encuentro un . pongo un cero
        //si encuentro un * pongo un 9 (una casilla como máximo va a tener 8 bombas alrededor)
        for(int i=0;i<f;i++){
            for (int j=0;j<c;j++){
                if(game[i][j]=='.'){
                    aux[i][j]=0;
                }
                if(game[i][j]=='*')
                    aux[i][j]=9;
            }
        }
        return aux;
    }
    /**
     * Calcula la nueva matriz contando las bombas cercanas
     * @param aux matriz inicializada a 0 y con 9 donde hay una bomba
     * @return matriz con las bombas inicializadas
     */
    private int[][] Convert(int[][] aux){
        //si encuentro un 9 sumo +1 a todas las casillas de alrededor si no es 9
        for(int i=0;i<f;i++){
            for (int j=0;j<c;j++){
                if(aux[i][j]==9){
                    //diagonal izq superior
                    if(i>0&&j>0&&aux[i-1][j-1]!=9){
                        aux[i-1][j-1]++;
                    }
                    //casilla superior
                    if(i>0&&aux[i-1][j]!=9){
                        aux[i-1][j]++;
                    }
                    //diagonal dch superior
                    if(i>0&&j+1<c&&aux[i-1][j+1]!=9){
                        aux[i-1][j+1]++;
                    }
                    //casilla izq
                    if(j>0&&aux[i][j-1]!=9){
                        aux[i][j-1]++;
                    }
                    //casilla dch
                    if(j+1<c&&aux[i][j+1]!=9){
                        aux[i][j+1]++;
                    }
                    //casilla izq inferior
                    if(i+1<f&&j>0&&aux[i+1][j-1]!=9){
                        aux[i+1][j-1]++;
                    }
                    //casilla inferior
                    if(i+1<f&&aux[i+1][j]!=9){
                        aux[i+1][j]++;
                    }
                    //diagonal dch inferior
                    if(i+1<f&&j+1<c&&aux[i+1][j+1]!=9){
                        aux[i+1][j+1]++;
                    }
                }
            }
        }
        return aux;
    }
    
    /**
     * Convierte matriz de enteros en String
     * @param aux matriz de enteros con el resultado
     * @return resultado convertido en string
     */
    private String MatriztoString (int[][] aux){
        String result="";
        for(int i=0;i<f;i++){
            for (int j=0;j<c;j++){
                if(aux[i][j]==9){
                    result=result+"*";
                }else{
                    result=result+aux[i][j];
                }
            }
            result=result+"\\n";
        }
        return result;
    }
}
