		//-------------------
		// 時間関係の関数
		//-------------------
		function getMaxWaitingTime(handupStudentList){
			max = 0;
			if(handupStudentList != null && handupStudentList.length >0){
				max = handupStudentList[0].waitingTimeBySecond;
			}
			return max;
		}
		
		function getDisplayTime(hour,minute,second){
			var time="";
			if(hour > 0){
				time += hour+" 時間 ";
			}
			if(minute > 0){
				time += minute+" 分 "
			}
			if(second> 0){
				time += second+" 秒 "
			}
			return time.trim();
		}
		
		function getDisplayTimeFromSecond(secondTime){
			const hour = Math.floor(secondTime / 3600);
			const minute = Math.floor((secondTime - hour * 3600) / 60);
			const second = secondTime - hour * 3600 - minute * 60;
			return getDisplayTime(hour,minute,second);
		}
		
		function getMaxTime(maxWaitingTime){
			if(maxWaitingTime <60){
				return 60;
			}else if(maxWaitingTime <300){
				return 300;
			}else if(maxWaitingTime <600){
				return 600;
			}else if(maxWaitingTime <1800){
				return 1800;
			}else if(maxWaitingTime <3600){
				return 3600;
			}else{
				return 3600;
			}
		}
		
		function getBarColor(maxWaitingTime, time){
			if(maxWaitingTime <60){
				return "green";
			}else if(maxWaitingTime <300){
				return "orange";
			}else if(maxWaitingTime <600){
				return "pink";
			}else if(maxWaitingTime <1800){
				return "red";
			}else if(maxWaitingTime <3600){
				return "gray";
			}else{
				return "gray";
			}
		}
		
		function getBarLength(maxWaitingTime, time, maxBarLength){
			const maxTime=getMaxTime(maxWaitingTime);
			return Math.floor(time/maxTime*maxBarLength);
		}