/**
 * @author Ali
 */
			var achievement;
			var amountofbadges=0;
			function badgebox(medal){
				$(document).ready(function(){
					var div = document.getElementById('popup-box-achievement');
					div.innerHTML = "";
					
					if(medal == "brons"){
						div.innerHTML = "<center> <img src='bronze.png' height='67' width='67'></img><br>CONGRATS!<br> YOU HAVE EARNED A BRONS BADGE</center><br>";
						amountofbadges++;
						increasebadgesunit();
					}else if(medal == "silver"){
						div.innerHTML = "<center> <img src='silver.png' height='67' width='67'></img><br>CONGRATS!<br> YOU HAVE EARNED A SILVER BADGE for answering 6 correct questions</center><br>";
						document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>BADGE COLLECTOR: NOVICE</h2> </center><br>";
						$('#achievement').fadeIn('slow');
						amountofbadges++;
						increasebadgesunit();
						setTimeout(fade_out, 3000);
					}else if(medal == "gold"){
						div.innerHTML = "<center> <img src='guld.png' height='67' width='67'></img><br>CONGRATS!<br> YOU HAVE EARNED A GOLD BADGE for answering 14 questions correct</center><br>";
						document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>BADGE COLLECTOR: EXPERT</h2> </center><br>";
						$('#achievement').fadeIn('slow');
						amountofbadges++;
						increasebadgesunit();
						setTimeout(fade_out, 3000);
					}else if(medal == "platina"){
						div.innerHTML = "<center> <img src='platinum.png' height='67' width='67'></img><br><br> YOU HAVE EARNED A PLATINA BADGE for answering all questions right</center><br>";
						document.getElementById('allright').innerHTML  = "<center><br>Achievement:<br> <h2>BADGE COLLECTOR: MASTER</h2> </center><br>";
						$('#allright').fadeIn('slow');
						amountofbadges++;
						increasebadgesunit();
					}
					
					var achievementBox = '#popup-box-achievement';
					
					$(achievementBox).fadeIn(250);
					
					var popMargTop = ($(achievementBox).height() + 24) / 2;
					var popMargLeft = ($(achievementBox).width() + 24) / 2;
					
					$(achievementBox).css({
					'margin-top'  : -popMargTop,
					'margin-left' : -popMargLeft
				});
				// if(isNaN(achievement)){
					// $('body').append('<div id="mask"></div>');
					// $('#mask').fadeIn(500);
					// return false;
				// }
				
				
				// if(isNaN(achievement)){
					// $('button.close,#mask').live('click',function(){
					// $('#mask, .popupInfo').fadeOut(400,function(){
						// $('#mask').remove();
					// });
// 					
					// return false;
				// });
				// }
			
			});
			
			$(document).keyup(function(e){
				
				if(e.keyCode == 27 && isNaN(achievement)){
					$('#mask, .popupinfo, #popup-box-achievement').fadeOut(400);
					return false;
					amountofbadges++;
					increasebadgesunit();
				}else if(e.keyCode == 27 && !isNaN(achievement)){
					$('.popupinfo, #popup-box-achievement').fadeOut(400);
					$('#achievement').fadeOut();
					return false;
				}
			});
			}
			
		function increasebadgesunit(){
			document.getElementById("playerstatsbadges").innerHTML ='Amount of badges earned: <strong>'+amountofbadges+'<strong>';
		}	
		
		
