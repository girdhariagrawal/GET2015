/**
 * @author Girdhari
 * Implement the scenario of Train Reservation System
 * Class Train will provide all the method related to train list 
 */
package trainreservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Train {
	
	private BufferedReader bufferReaderObj;
	List<TrainDetail> trainList = new ArrayList<TrainDetail>();
	List<String> trainListSingleRow = new ArrayList<String>();
	final String TRAIN_LIST_FILE = new File("src/trainList.csv").getAbsolutePath();// will contain the address of file that contain list of train
	final String STRING_DELIMITER = ",";
	private List<TrainDetail> filteredTrainList;
	
	/*starting of listTrain method*/
	/*This method will list up all the train according to their type i.e. Passenger or Goods*/
	public List<TrainDetail> listTrain(int type) throws IOException{
		String temp = "";
		
		try{
			bufferReaderObj = new BufferedReader(new FileReader(TRAIN_LIST_FILE));
			while((temp = bufferReaderObj.readLine()) != null){
				int index = 0;
				String trainData[] = temp.split(STRING_DELIMITER);
				TrainDetail trainDetail = null;		//new object creation of trainDetail class
				if(type == 1 && trainData[2].equalsIgnoreCase("P")){
					trainDetail = new TrainDetail();
					trainDetail.setTrainNumber(trainData[index++]);				//set train number
					trainDetail.setTrainName(trainData[index++]);				//set train name
					trainDetail.setTypeTrain(trainData[index++]);				//set train type
					trainDetail.setSource(trainData[index++]);					//set train source
					trainDetail.setDestination(trainData[index++]);				//set train destination
					trainDetail.setDuration(trainData[index++]);				//set train duration
					trainDetail.setSeatAvailable(trainData[index]);			//set train seat availability
				}
				else if(type == 2 && trainData[2].equalsIgnoreCase("G")){
					trainDetail = new TrainDetail();
					trainDetail.setTrainNumber(trainData[index++]);				//set train number
					trainDetail.setTrainName(trainData[index++]);				//set train name
					trainDetail.setTypeTrain(trainData[index++]);				//set train type
					trainDetail.setSource(trainData[index++]);					//set train source
					trainDetail.setDestination(trainData[index++]);				//set train destination
					trainDetail.setDuration(trainData[index++]);				//set train duration
					trainDetail.setSeatAvailable(trainData[index]);			//set train weight availability
				}
				if(trainDetail != null)
				trainList.add(trainDetail);
			}
		}
		catch(FileNotFoundException exception){
			exception.printStackTrace();
		}
		catch(IOException exception){
			exception.printStackTrace();
		}
		catch(Exception exception){
			System.out.print("Unhandled Error Occured!!");
		}
		return trainList;
	}
	/*End of listTrain method*/

	/*Starting of filterList function According to start and end*/
	List<TrainDetail> filterList(List<TrainDetail>trainList, String source, String destination){
		List<TrainDetail> filteredTrainList = new ArrayList<TrainDetail>();
		for(int counter=0 ; counter < trainList.size() ; counter++){
			TrainDetail tempTrainDetail = trainList.get(counter);
			TrainDetail newTrainDetail = new TrainDetail();
			if(tempTrainDetail.getSource().equalsIgnoreCase(source) && tempTrainDetail.getDestination().equalsIgnoreCase(destination)){
				newTrainDetail.setTrainNumber(tempTrainDetail.getTrainNumber());				//set train number
				newTrainDetail.setTrainName(tempTrainDetail.getTrainName());				//set train name
				newTrainDetail.setTypeTrain(tempTrainDetail.getTypeTrain());				//set train type
				newTrainDetail.setSource(tempTrainDetail.getSource());					//set train source
				newTrainDetail.setDestination(tempTrainDetail.getDestination());				//set train destination
				newTrainDetail.setDuration(tempTrainDetail.getDuration());				//set train duration
				newTrainDetail.setSeatAvailable(tempTrainDetail.getSeatAvailable());			//set train weight availability
				filteredTrainList.add(newTrainDetail);
			}
		}
		return filteredTrainList;
		
	}
	/*End of filterList function (According to start and end)  */
	
	/*Starting of filterList function According to availability of seat */
	List<TrainDetail> filterList(List<TrainDetail> trainList, int numberOfSeat){
		filteredTrainList = null;
		TrainDetail tempTrainDetailObj=new TrainDetail();
		int index=0;
		for(int counter=0 ; counter < trainList.size() ; counter++){
			if(Integer.parseInt(trainList.get(counter).getSeatAvailable()) > numberOfSeat){
				tempTrainDetailObj.setTrainNumber(trainList.get(index).getTrainNumber());
				tempTrainDetailObj.setTrainName(trainList.get(index).getTrainNumber());
				tempTrainDetailObj.setTypeTrain(trainList.get(index).getTypeTrain());
				tempTrainDetailObj.setSource(trainList.get(index).getSource());
				tempTrainDetailObj.setDestination(trainList.get(index).getDestination());
				tempTrainDetailObj.setDuration(trainList.get(index).getDuration());
				tempTrainDetailObj.setSeatAvailable(trainList.get(index).getSeatAvailable());
				filteredTrainList.add(tempTrainDetailObj);
			}
		}
		return filteredTrainList;
		
	}
	/*End of filterList function (According to availability of seat) */
	
}
