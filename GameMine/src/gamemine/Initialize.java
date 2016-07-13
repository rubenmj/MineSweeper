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
        //split string in different parts
        String[]parts = mineField.split("\\n");
        //check that size of the parts are equal
        for(int i=0;i<parts.length-1;i++){
            if(parts[i].length()!=parts[i+1].length())
                throw new IllegalArgumentException();
        }
        //create matrix
        game=new char[parts.length][parts[0].length()];
        //check that in the String there is . or *
        for(int i=0;i<parts.length;i++){
            for(int j=0;j<parts[i].length();j++){
                //if the character is valid it is included in the matrix
                if(parts[i].charAt(j)=='.'||parts[i].charAt(j)=='*'){
                    game[i][j]=parts[i].charAt(j);
                }else
                    throw new IllegalArgumentException();
                    
            }
        }
    }
    
    public String getHintField() throws IllegalStateException{
        //check if setMineField has been called, if not it will throw an exception
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
        //if there is a . change to 0
        //if there is a * change to 9 (a cell can't have more than 8 bombs around itself)
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
     * Calculate the new matix counting the bombs around 
     * @param aux matrix is initialized to 0 and to 9 in the place of a bomb
     * @return matrix with all initialized bombs
     */
    
    private int[][] Convert(int[][] aux){
        //when I have a bomb (9) I add 1 to all cells around which are not bombs (9)
        for(int i=0;i<f;i++){
            for (int j=0;j<c;j++){
                if(aux[i][j]==9){
                    //upper left side cell 
                    if(i>0&&j>0&&aux[i-1][j-1]!=9){
                        aux[i-1][j-1]++;
                    }
                    //upper cell
                    if(i>0&&aux[i-1][j]!=9){
                        aux[i-1][j]++;
                    }
                    //upper right side cell
                    if(i>0&&j+1<c&&aux[i-1][j+1]!=9){
                        aux[i-1][j+1]++;
                    }
                    //left side cell
                    if(j>0&&aux[i][j-1]!=9){
                        aux[i][j-1]++;
                    }
                    //right side cell
                    if(j+1<c&&aux[i][j+1]!=9){
                        aux[i][j+1]++;
                    }
                    //bottom left side cell 
                    if(i+1<f&&j>0&&aux[i+1][j-1]!=9){
                        aux[i+1][j-1]++;
                    }
                    //bottom cell
                    if(i+1<f&&aux[i+1][j]!=9){
                        aux[i+1][j]++;
                    }
                    //bottom right side cell
                    if(i+1<f&&j+1<c&&aux[i+1][j+1]!=9){
                        aux[i+1][j+1]++;
                    }
                }
            }
        }
        return aux;
    }
    
    /**
     * Convert integer matrix into String
     * @param aux integer final matrix
     * @return String with final result
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
