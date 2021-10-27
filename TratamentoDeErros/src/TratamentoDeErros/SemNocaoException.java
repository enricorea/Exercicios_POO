package TratamentoDeErros;

public class SemNocaoException extends Exception{
	
	@Override
		public String getMessage() {
				return "Pessoa sem noção";
		}

}
