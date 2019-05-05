int sensor = 0;

int btn = 0;

int btn2 = 0;

int go = 0;

int locked = 0; 

int waiting = 1; 

int unlocked = 3; 

int state = locked; 


void setup()
{
  pinMode(2, INPUT);
  pinMode(4, INPUT);
  pinMode(7, INPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(10, OUTPUT);
}

void loop()
{
  sensor = digitalRead(2);
  btn = digitalRead(4);
  btn = digitalRead(7);
  boolean pushed = false; 
  if (sensor == HIGH) {
    digitalWrite(12, LOW);
    if (state == locked) {
    	state = waiting; 
    }
    while (state == waiting) {
      digitalWrite(13, HIGH);
      btn = digitalRead(4);
      btn2 = digitalRead(7);
      
      if (btn == HIGH) {
      	pushed = true; 
      } 
      
      if (pushed && btn2 == HIGH) {
      	state = unlocked; 
      }
      
      if ((btn == HIGH || btn2 == HIGH) && state == waiting){
      	digitalWrite(13, LOW);
        delay(200);
        digitalWrite(13, HIGH);
      }//if-gul
    }//while not unlocked
  if (state == unlocked) {
      digitalWrite(13, LOW);
      digitalWrite(10, HIGH);
      delay(6000); // Wait for 6000 millisecond(s)
      digitalWrite(10, LOW);
    } //if-grønn
      }//if 1
  digitalWrite(12, HIGH);
}