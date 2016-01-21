import java.util.Scanner;


public class Calculator {
	private int priority;

	//Gets priority
	private static int getPriority(String current){
		int priority = 0;
		switch (current) {
		case "*":
		case "/":{
			priority=3;
		}
		break;
		case "+":
		case "-":{
			priority=2;
		}
		break;
		case "(":
		case ")":{
			priority=1;
		}
		default:
			break;
		}
		return priority;
	}

	//Realizes if the character is a number
	private static boolean isNum(String element){
		if((element.charAt(element.length()-1)>=48 && element.charAt(element.length()-1)<=57) || (element.charAt(element.length()-1)==46))
			return true;
		else return false;
	}

	//Operates and returns the answer
	private static double Operate(double o1, double o2, String operator){
		double answer = 0;
		switch (operator) {
		case "+":{
			return (answer=(o1 + o2));
		}
		case "-":{
			return (answer=(o1 - o2));
		}
		case "/":{
			return (answer=(o1 / o2));
		}
		case "*":{
			return (answer=(o1 * o2));
		}
		default:
			break;
		}
		return answer;

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter an equation :");
		Queue interpretor = new Queue();
		Stack in2post = new Stack();
		//Let user enter an equation
		String infix="";
		infix=keyboard.nextLine();
		System.out.println("THE PREFIX " + infix);
		//Loop through entire string expression and organise the terms into a queue that can be processed
		for(int i=0;i<infix.length();i++){
			char insert=infix.charAt(i);
			String temp="";
			temp+=insert;
			if(temp.equalsIgnoreCase(" "))
				continue;
			if(!isNum(temp)){
				String inserti="";
				inserti+=insert;
				interpretor.enQ(inserti);
				continue;
			}

			else{
				String insertion ="";
				insertion+=insert;
				if(i!=infix.length()-1){
					int next=i+1;
					char nextchar= infix.charAt(next);
					for(next=i+1;next<infix.length();next++){
						nextchar= infix.charAt(next);
						String temp2="";
						temp2+=nextchar;
						if(!isNum(temp2)){
							break;
						}
						insertion+=nextchar;
					}
					i=next-1;
				}
					interpretor.enQ(insertion);
				}
			}
		
			Queue postfixExpression = new Queue();
			String postfix="";

			//CONVERTING INFIX TO POSTFIX
			int qsize=interpretor.getSize();
			for(int i=0;i<qsize;i++){//Loop through entire String
				//Create a string that stores the front of the queue
				String currant=interpretor.front().toString();
				interpretor.deQ();
				if(currant.equals("(")){
					in2post.push(currant);
					continue;
				}
				if(currant.equals(")")){
					while((!in2post.getTail().toString().equals("("))){//Keep adding a stack and popping from stack if you hit )
						postfixExpression.enQ(in2post.getTail().toString());
						in2post.pop();	
					}
					in2post.pop();	//Pop again so you pop )
					continue;
				}

				if(isNum(currant)){
					postfixExpression.enQ(currant);//If its a number add to postfix
					continue;
				}

				if(in2post.isEmpty()){
					in2post.push(currant);//If empty add whatever to stack
					continue;
				}

				else {	//While the precedence of the tail of stack is higher than current. Keep adding to postfix and keep popping
					while((getPriority(in2post.getTail().toString())) >= ((getPriority(currant)))){
						if((!in2post.getTail().toString().equals("(")) && (!in2post.getTail().toString().equals(")"))){
							postfixExpression.enQ(in2post.getTail().toString());
						}
						in2post.pop();	

						if(in2post.isEmpty()) // If empty then break
							break;				
					}
					in2post.push(currant); //Finally push
				}
			}


			//Whatever is on the stack as long as its not ( or ) keep adding to postfix and pop
			while(!in2post.isEmpty()){
				if(((!in2post.getTail().toString().equals("(")) && (!in2post.getTail().toString().equals(")")))){
					postfixExpression.enQ(in2post.getTail().toString());
				}
				in2post.pop();
			}

			System.out.println("THE POSTFIX  " + postfixExpression.display());

			//POSTFIX EVALUATION
			int q2size=postfixExpression.getSize();
			for(int j=0;j<q2size;j++){
				String currant=postfixExpression.front().toString();
				postfixExpression.deQ();
				if(isNum(currant)){
					in2post.push(currant);//Add numbers to the stack
					continue;
				}
				else{//Pop last 2 operands and operate with the answer
					double operand2=Double.parseDouble((in2post.getTail().toString()));
					in2post.pop();
					double operand1=Double.parseDouble((in2post.getTail().toString()));
					in2post.pop();
					double answer=Operate(operand1, operand2, currant);
					in2post.push(answer);//Push answer on stack
				}
			}
			double finalanswer= Double.parseDouble((in2post.getTail().toString()));
			System.out.println("FINAL ANSWER " + finalanswer);

			keyboard.close();
			
		}
	}
