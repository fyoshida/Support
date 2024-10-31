		//-------------------
		// グラフ描画
		//-------------------
		function drawChart(student,handupStudentList){
			var chartElement = document.getElementById("chart");
			if(handupStudentList == null || handupStudentList.length==0){
				chartElement.style.display="none";
				return;
			}

			clearChildElement(chartElement);

			chartElement.style.display="block";
			addChildOrderElement(chartElement,handupStudentList);
			addChildBarElement(chartElement,handupStudentList);
			chartElement.style.height=(handupStudentList.length*25)+"px";
			
			const unitElement = document.getElementById("unitTime");
			const unitTime=getDisplayTimeFromSecond(getMaxTime(maxWaitingTime));
			unitElement.innerText="("+unitTime+")";

		}
		
		function clearChildElement(chartElement){
			while (chartElement.firstChild) {
    			chartElement.removeChild(chartElement.firstChild);
			}
		}
		
		function addChildOrderElement(chartElement,handupStudentList){
			for( var i=0; i<handupStudentList.length; i++){
				const orderElement=document.createElement('div');
				orderElement.setAttribute("id","rank"+(i+1));
				orderElement.setAttribute("class","rank");
				orderElement.style.position ="absolute"
				orderElement.style.left = "-30px"
				orderElement.style.width= "20px";
				orderElement.style.height = "20px";
				orderElement.style.textAlign="right"
				orderElement.innerText=(i+1);
				if(student.handPriority == i+1){
					orderElement.style.top = 6+i*25+"px";
					orderElement.style.fontSize="20px";
					orderElement.style.color="blue";
				}else{
					orderElement.style.top =10+i*25+"px";
					orderElement.style.fontSize="15px";
					orderElement.style.color="black";
				}
				chartElement.appendChild(orderElement);
			}
		}
				
		function addChildBarElement(chartElement,handupStudentList){
			for( var i=0; i<handupStudentList.length; i++){
				const barElement=document.createElement('div');
				barElement.setAttribute("id","wait"+(i+1));
				barElement.setAttribute("class","wait");
				barElement.style.position ="absolute"
				barElement.style.top =10+i*25+"px";
				barElement.style.left ="5px";
				barElement.style.width= getBarLength(maxWaitingTime,handupStudentList[i].waitingTimeBySecond,120)+"px";
				barElement.style.height = "20px";
				if(student.handPriority == i+1){
					barElement.style.background= "#33CCFF";
				}else{
					barElement.style.background= getBarColor(maxWaitingTime,handupStudentList[i].waitingTimeBySecond);
				}
				chartElement.appendChild(barElement);
			}
		}