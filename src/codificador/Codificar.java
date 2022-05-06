package codificador;

public class Codificar {
	private char[] texto;
	private String[] codificadoArray;
	private String codificado;
	private int y;
	public final char[] abece= {'$','�','�','�','�','�','\u00AD','�','a','(','.','b',' ','+','c','<','d','*','e',')','%','f','&','g','/','h','\'','i','^','>','j','\\','k','�','l','�','m','�','n','~','�','`','o','@','p','4','q','1','r','9','s','8','t','7','u','6','v','5','w','?','x','3','y','2','\t','z',',','0','�','B','_','A','\n','}','�','[','�','C',':','D','E','F',';','-','�','�','�','�','=','�','�','�','�','�','�','�','{','�','�','�','�','�','�','G','�','�','H','�','�','I','"','|','J','#','K','�','L','�','M','N','�','�','O','�','�','�','�','�','�','P','Q','R','S','T','U','V','W','X','Y','Z',']','!'};
	
	public String codTexto(char[] cadena,int onda) {
		codificado="";
		for(int i=0;i<cadena.length;i++) {
			boolean a = false;
			for(int x=0;x<abece.length;x++) {
				
				if(cadena[i]==abece[x]) {
					a=true;
					y=x+i+onda;
					y%= abece.length;
					codificado+=abece[y];
				}
			}
			if(!a){
				System.out.println("MIEDO con "+cadena[i]);
			}
			
		}
		return codificado;
	}

	public String decodTexto(char[] cadena, int onda) {
		codificado="";
		for(int i=0;i<cadena.length;i++) {
			for(int x=0;x<abece.length;x++) {
				if(cadena[i]==abece[x]) {
					y=x-i-onda;

					while(y<0) {
						y=y+abece.length;
					}
					codificado+=abece[y];
					break;
				}
			}
		}
		return codificado;
	}
}
