#include<iostream>
#include<vector>
using namespace std;
vector<int>::iterator iter;

int main(void){
	int slot;
	int num_moving;
	int count=0;
	
	cin>>slot; //초창기의 주차 칸 개수 
	cin>>num_moving; //빈공간 주차 칸 에서 차가 들어가고 나간갯수
	
	int arr_in_and_out[num_moving];
	vector<int> v(slot) ; //크기 slot이고 0으로 초기화된 벡터  
	
	for(int i=0;i<num_moving;i++){ //양수 음수 모두 입력받기  
		cin>>arr_in_and_out[i];
	}
	
	for(int i=0;i<num_moving;i++){
		
		if(arr_in_and_out[i]>0){ //입력받은 번호판이 양수이면
		
			if(count>=slot){ //차 수가 주차 공간보다 많을때 
				 
				v.resize(slot*2);//벡터 크기(주차공간 ) 2배로 늘려주기 
				slot*=2;
			}
			v.insert(v.begin()+count,arr_in_and_out[i]); //벡터의 처음부터 넣기 
			count++ ;
		}
		
		
		else{// arr_in_and_out 이 입력받은 번호판이 음수이면 
			for(int j=0;j<v.size();j++){	 
				if(v.at(j)==arr_in_and_out[i]){ //벡터에 양수로된 음수의 번호판이 있으면 양수 번호판을 뺸다 
					v.erase(v.begin()+j); //양수 번호판 제거  
				}
				
				
				/*else{ //벡터에 음수입력받은 번호판이 없으면  그 음수 무시  
					  
				} */
				
				
			}
		}
		
		
	}
	
	
	for(iter = v.begin(); iter!=v.end();iter++){
		cout<<*iter<<" "<<endl;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}