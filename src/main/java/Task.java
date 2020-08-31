public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Boolean markAsDone() {
        if(this.isDone){
            return false;
        }
        this.isDone = true;
        return true;
    }


    public void printTask() {
        System.out.println("[" + this.getStatusIcon() + "] " +this.description);
    }

}