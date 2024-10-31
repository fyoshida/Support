		//-------------------
		// 学生情報の表示設定
		//-------------------
		function setStudentInfo(num, obj,selectStudent) {
			document.getElementById("pcname" + num).innerText = obj.pcId;
			document.getElementById("status" + num).style.display = "none";
			setStudentInfoStatus(num,obj,selectStudent);
			setStudentInfoPriority(num,obj);
			setStudentInfoTimebar(num,obj,maxBarLength);
			setStudentInfoTime(num,obj);
		}

		function setStudentInfoStatus(num,obj,selectStudent){
			var color="white";
			if(obj==selectStudent){
				color="orange";
			}else if(obj.helpStatus=="None"){
				color="white";
			}else if(obj.helpStatus=="Troubled"){
				color="yellow";
			}else if(obj.helpStatus=="Supporting"){
				color="lightgreen";
			}
			document.getElementById("student" + num).style.backgroundColor=color;
		}

		function setStudentInfoPriority(num,obj){
			document.getElementById("priority" + num).innerText = obj.handPriority;
			if(obj.handPriority == "999"){
				document.getElementById("priority" + num).style.display = "none";				
			}else{
				document.getElementById("priority" + num).style.display = "block";	
			}
		}
		
		function setStudentInfoTime(num,obj){
			const time = obj.waitingTimeBySecond;
			document.getElementById("time" + num).innerText=getDisplayTimeFromSecond(time);
		}

		function setStudentInfoTimebar(num,obj,maxBarLength){
			const time = obj.waitingTimeBySecond;

			var timeBarLength = getBarLength(maxWaitingTime,time,maxBarLength);
			var timeBarColor="#33CCFF";
			if(typeof student === "undefined"){
				timeBarColor=getBarColor(maxWaitingTime,time);
			}
			

			if(obj.helpStatus=="Troubled"){
				document.getElementById("timebar" + num).style.display="block";
				document.getElementById("timebar" + num).style.width = timeBarLength+"px";
				document.getElementById("timebar" + num).style.background = timeBarColor;
			}else{
				document.getElementById("timebar" + num).style.display="none";
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
				return "black";
			}else{
				return "black";
			}
		}
		
		function getBarLength(maxWaitingTime, time,maxBarLength){
			if(maxWaitingTime <60){
				return Math.floor(time/60*maxBarLength);
			}else if(maxWaitingTime <300){
				return Math.floor(time/300*maxBarLength);
			}else if(maxWaitingTime <600){
				return Math.floor(time/600*maxBarLength);
			}else if(maxWaitingTime <1800){
				return Math.floor(time/1800*maxBarLength);
			}else if(maxWaitingTime <3600){
				return Math.floor(time/3600*maxBarLength);
			}else{
				return maxBarLength;
			}
		}
