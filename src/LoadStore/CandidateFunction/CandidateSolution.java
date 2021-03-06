package LoadStore.CandidateFunction;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

import LoadStore.Project;
import LoadStore.Student;
public class CandidateSolution implements Comparable< CandidateSolution >{
    private List<StudentProjectAllocation> studentProjectAllocations= new ArrayList<>();

    private Double energyValue;
    Random rand=new Random();
    EnergyFunction energy=new EnergyFunction();
    FitnessFunction fitness=new FitnessFunction();
    
    public EnergyFunction getEnergy() {
		return energy;
	}
	public CandidateSolution(List<Student> students){
		for(Student student : students) {
		    Project randomProject=student.getProjectPreferences().get(rand.nextInt(student.getProjectPreferences().size()));
		    StudentProjectAllocation candidate=new StudentProjectAllocation(student,randomProject);
		    studentProjectAllocations.add(candidate);
        }
		System.out.println(studentProjectAllocations.size());
        energyValue=energy.checkEnergy(studentProjectAllocations);
    }
    public CandidateSolution(){
       // System.out.println("new candidate");
    }
    

    @Override
    public int compareTo(CandidateSolution o) {
        o.updateEnergyValue();
        updateEnergyValue();
        if(o.energyValue==null){

            System.out.println("null pointer");
        }
        return this.energyValue.compareTo(o.energyValue);
    }

    public Double getEnergyValue() {
        return energyValue;
    }

    public void updateEnergyValue() {
        this.energyValue=energy.checkEnergy(studentProjectAllocations);
    }
    
    public void change(List<Project> projects) {
    	for (int i = 0; i < projects.size()/10; i++) {
    		Project randomProject=projects.get(rand.nextInt(projects.size()));
        	this.studentProjectAllocations.get(i).setProject(randomProject);
            updateEnergyValue();
        }
    } 
    public void randomChange(int numChanges) {
       for(int i=0;i<numChanges;i++){
        studentProjectAllocations.get(rand.nextInt(studentProjectAllocations.size())).changeProjectAllocation();
        
       }
       updateEnergyValue();
    }

    public Double checkEnergy(){
        return energy.checkEnergy(studentProjectAllocations);
    }

    public List<StudentProjectAllocation> getStudentProjectAllocations() {
        return studentProjectAllocations;
    }

    public void setStudentProjectAllocations(List<StudentProjectAllocation> studentsProjectAllocations) {
studentProjectAllocations.clear();
        for(StudentProjectAllocation allocation: studentsProjectAllocations){
    this.studentProjectAllocations.add(new StudentProjectAllocation(allocation.getStudent(), allocation.getProject()));
}
    }
public void toEnergyString(){
    energy.energyString(studentProjectAllocations);
}
public List<Double> getData() {
List<Double> list =new ArrayList<Double>();
list.add((double) this.getEnergy().checkNumOfDuplicates(this.getStudentProjectAllocations()));
list.add((double) this.getEnergy().checkForMismatchingStream(this.getStudentProjectAllocations()));
list.add((double) this.getEnergy().checkNumOfProjectsNotPreferred(this.getStudentProjectAllocations()));
list.add((double) this.getEnergy().checkAveragePreferenceForProject(this.getStudentProjectAllocations()));
return list;
}
    
}
	



    

