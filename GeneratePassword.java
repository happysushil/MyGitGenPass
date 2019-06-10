/**
 * @Author Sushil Sharma
 *
 */

class GeneratePassword {

	/**
	 * @param args
	 */

	protected static char[] capitalChars = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

	protected static char[] numberslist = {
		'0','1','2','3','4','5','6','7','8','9'
        };

	/*protected static char[] remainingChars = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i', 'j', 'k', 'm', 'n','o',
        'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J', 'K','L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0','1','2', '3', '4', '5', '6', '7', '8', '9'
    };*/
	/* The random number generator. */
    protected static java.util.Random r;
    /*
     * This method i.e. "validateInputNumbers "validates 3 input arguments.
     * First argument is length of password
     * Second argument tells minimum number of Capitals needed.
     * Third argument tells minimum number of digits needed.
     * Any of 3 arguments should not be a negative number.
     * Length of password can not be less than the sum of two other arguments
     * Length of password can also not be less than either of the other two arguments
     * */
    public static boolean validateInputNumbers(int length,int minCapitals,int minNumbers)
    {
    	if ((length < minNumbers+minCapitals)||(length < minNumbers) || (length < minCapitals) ||
    	    (length<=0) || (minCapitals<0) || (minNumbers<0)
    	    )
    		return false;
    	else
    		return true;
    }

    /*
     * Method "getRandomPassword" takes 3 parameters and generates a random password as per
     * following rules:
     * int length: This is the minimum length of the password that SHOULD be returned.
     * Note that the length may be increased by the method to comply to other
     * rules - minCapitals and minNumbers.
     * int minCapitals: This is the minimum number of capital letters that SHOULD be
     * present in the returned string.
     * int minNumbers: This is the minimum number of digits (0-9) that SHOULD be present
     * in the returned string.The remaining characters after satisfying the minimum
     * constraints may be filled with normal alphabets, capital alphabets and numbers.
     * The password should be random in nature.
     */

    public static String getRandomPassword(int length,int minCapitals,int minNumbers){
		int randomnumber,passwordIterator;
		StringBuffer sb = new StringBuffer();
		int capitalsIterator,numbersIterator;

		r = new java.util.Random();
		capitalsIterator=numbersIterator=passwordIterator=0;

		/*for (int passwordIterator=0; passwordIterator < length; passwordIterator++)
		{*/
			while(capitalsIterator < minCapitals){
				randomnumber = r.nextInt(capitalChars.length);
				sb.append(capitalChars[randomnumber]);
				passwordIterator++;
				capitalsIterator++;
			}
			while(numbersIterator < minNumbers){
				randomnumber = r.nextInt(numberslist.length);
				sb.append(numberslist[randomnumber]);
				passwordIterator++;
				numbersIterator++;
			}
			/*if(passwordIterator < length){
				randomnumber = r.nextInt(remainingChars.length);
				sb.append(remainingChars[randomnumber]);
			}*/
			//if(passwordIterator < length){
			while(passwordIterator < length){
				int Low = 32; int High = 127;
				randomnumber = r.nextInt(High-Low) + Low;
				char digit = (char) randomnumber;
				sb.append(digit);
				passwordIterator++;
			}
		//}

		for(int revpassIterator=sb.length() - 1; revpassIterator>1; revpassIterator--)
		{
			int swapWith = r.nextInt(sb.length());
			char temp = sb.charAt(swapWith);
			sb.setCharAt(swapWith, sb.charAt(revpassIterator));
			sb.setCharAt(revpassIterator, temp);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		// Get 3 numbers from command line and pass them to getRandomPassword after validations
		int passLength, passminCapitals, passminNumbers;
		if (args.length != 3) {
			System.out.println("Please enter three natural numbers first should be biggest of three!");
		}else{
			try {
					passLength = Integer.parseInt(args[0]);
					passminCapitals = Integer.parseInt(args[1]);
					passminNumbers = Integer.parseInt(args[2]);
					if(validateInputNumbers(passLength,passminCapitals,passminNumbers)==true)
						System.out.println("password generated: " + getRandomPassword(passLength, passminCapitals, passminNumbers));
					else
						System.out.println("Please enter three natural numbers first should be biggest of three!");
			}catch (NumberFormatException e) {
		        System.err.println("All 3 Arguments must be natural numbers");
		        System.exit(1);
		    }

		}
	}

}
