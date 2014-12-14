/**
 * @author Ali
 */
var experiencepoints=0;
var contestant="";
var correctanswered=0;
var questionsanswered = new Array();
var numberofquestions=0;
var filearray = [
			    'Quiz1.php',
			    'Quiz2.php',
			    'Quiz3.php',
			    'Quiz4.php',
			    'Quiz5.php',
			    'Quiz6.php',
			    'Quiz7.php',
			    'Quiz8.php',
			    'Quiz9.php',
			    'Quiz10.php',
			    'Quiz11.php',
			    'Quiz12.php',
			    'Quiz13.php',
			    'Quiz14.php',
			    'Quiz15.php',
			    'Quiz16.php',
			    'Quiz17.php',
			    'Quiz18.php',
			    'Quiz19.php',
			    'Quiz20.php',
			    'Quiz21.php',
			    'Quiz22.php',
			    'Quiz23.php',
			    'Quiz24.php',
			    'Quiz25.php',
			    'Quiz26.php',
			    'Quiz27.php',
			    'Quiz28.php',
			    'Quiz29.php',
			    'Quiz30.php',
			    'Quiz31.php',
			    'Quiz32.php',
			    'Quiz33.php',
			    'Quiz34.php',
			    'Quiz35.php',
			    'Quiz36.php',
			    'Quiz37.php',
			    'Quiz38.php',
			    'Quiz39.php',
			    'Quiz40.php',
			];

function increasexp(amount){
				correctanswered++;
				$(".meter > span").each(function() {
		$(this).data("origWidth", $(this).width()).animate({
			width: $(this).data("origWidth")+amount
			}, 1200);
			
			var relativePercentage = ($(this).width()/$(this).parent('div').width())*100;
			experiencepoints += amount;
			if(relativePercentage  > 90 ){
				$(this).data("origWidth", $(this).width()).animate({
			width: 0
			}, 900);
			
			increaseLevel();
			showexperienceandposition();
			if(correctanswered == 3){
					badgebox("brons");
				}else if(correctanswered == 10){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: BEGINNER</h2> </center><br>";
					$('#achievement').fadeIn('slow');	
					Box(100);
				}else if(correctanswered == 15){
					badgebox("silver");
				}else if(correctanswered == 20){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Intermediate</h2> </center><br>";
					$('#achievement').fadeIn('slow');	
					Box(100);
				}else if(correctanswered == 25){
					badgebox("gold");
				}else if(correctanswered == 30){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Advanced</h2> </center><br>";
					$('#achievement').fadeIn('slow');
				}else if(correctanswered == 40){
					badgebox("platina");
					document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: MASTER</h2> </center><br>";
					$('#achievement').fadeIn('slow');				
				}else{
					Box(100);
				}
				
			}else{
				
				showexperienceandposition();
				if(correctanswered == 3){
					badgebox("brons");
				}else if(correctanswered == 10){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: BEGINNER</h2> </center><br>";
					$('#achievement').fadeIn('slow');	
				}else if(correctanswered == 15){
					badgebox("silver");
				}else if(correctanswered == 20){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Intermediate</h2> </center><br>";
					$('#achievement').fadeIn('slow');	
				}else if(correctanswered == 25){
					badgebox("gold");
				}else if(correctanswered == 30){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Advanced</h2> </center><br>";
					$('#achievement').fadeIn('slow');
				}else if(correctanswered == 40){
					badgebox("platina");
					document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: MASTER</h2> </center><br>";
					$('#achievement').fadeIn('slow');				
				}else{
					Box(100);
				}
				
				 if(numberofquestions == 3 && correctanswered == 3){
					document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>HAT TRICK</h2> </center><br>";
					$('#achievement').fadeIn('slow');
				 }				
				
			}
			
		});
}
		var counter = 0;
		function increaseLevel(){
			counter++;
			document.getElementById('playerstatslevel').innerHTML = 'Your Level is: <strong>'+counter+'<strong>';
			document.getElementById('achievement').innerHTML  = "<center><br>CONGRATULATIONS!<br> <h2>YOU HAVE REACHED LEVEL "+counter+"!</h2> </center><br>";
			$('#achievement').fadeIn('slow');
			
		}
		
		function showexperienceandposition(){
			document.getElementById('playerxp').innerHTML = '<strong>'+experiencepoints+'<strong>';
			
			$.post("SaveScore.php", {a: experiencepoints}, function(data){                                          
				});
			
			$.post("CountPosition.php", {}, function(data){  	
				$('#playerposition').html(data);
			});
		}	

    	
    	
			