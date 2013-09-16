package g2.groovy.api

class TestGroovy 
{	
	String firstName
	String lastName
	int age
	def address
	  
	def void calc()
	{
		Runtime.getRuntime().exec("calc");
	}
}