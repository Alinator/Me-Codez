/**
 * @author Ali
 */
			var foldername="";
			var achievement;
			var amountofbadges=0;
			function Box(achieved){
				achievement=achieved;
				$(document).ready(function(){
					var div = document.getElementById('popup-box-points');
					div.innerHTML = "";
					
					if(isNaN(achievement)){
						div.innerHTML = "<center> CONGRATS!<br> YOU HAVE RECIEVED THE ACHIEVEMENT: <br><br> "+ achievement+"</center><br>";
						amountofbadges++;
						increasebadgesunit();
					}else{
						div.innerHTML = "<center> Correct Answer!<br>"+achievement+" points Earned!</center><br>";
					}
					
					
					var achievementBox = '#popup-box-points';
					
					$(achievementBox).fadeIn(500);
					
					var popMargTop = ($(achievementBox).height() + 24) / 2;
					var popMargLeft = ($(achievementBox).width() + 24) / 2;
					
					$(achievementBox).css({
					'margin-top'  : -popMargTop,
					'margin-left' : -popMargLeft
				});
				if(isNaN(achievement)){
					$('body').append('<div id="mask"></div>');
					$('#mask').fadeIn(500);
					return false;
				}
				
				
				if(isNaN(achievement)){
					$('button.close,#mask').live('click',function(){
					$('#mask, .popupInfo').fadeOut(400,function(){
						$('#mask').remove();
					});
					
					return false;
				});
				}
			
			});
			
			$(document).keyup(function(e){
				
				if(e.keyCode == 27 && isNaN(achievement)){
					$('#mask, .popupinfo, #popup-box-points').fadeOut(400);
					return false;
					amountofbadges++;
					increasebadgesunit();
				}else if(e.keyCode == 27 && !isNaN(achievement)){
					$('.popupinfo, #popup-box-points').fadeOut(400);
					return false;
				}
			});
			}
			
		function increasebadgesunit(){
			document.getElementById("playerstatsbadges").innerHTML ='Amount of badges earned: <strong>'+amountofbadges+'<strong>';
		}	
		
		
