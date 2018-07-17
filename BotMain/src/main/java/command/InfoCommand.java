package command;

import jpa.entity.User;

import java.util.List;

public class InfoCommand extends AbstractCommand {

    private Person person;
    private int iterator;

    @Override
    public List<Object> execute(String message, User user) {
        putPhases(phraseUtil.choosePerson());
        return completeExecution();
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        if(person == null || !person.hasNext(iterator)){
            choosePerson(message);
            return completeExecution();
        } else {
            return getInfo();
        }
    }

    private void choosePerson(String message) {
        if(message.equals(Person.NASTYA.getName())){
            person = Person.NASTYA;
            getInfo();
        } else {
            putPhases(phraseUtil.choosePerson());
            iterator = 0;
            person = null;
        }
    }

    private List<Object> getInfo(){
        if (person.hasNext(iterator)){
            putPhases(phraseUtil.personInfo(person.name(), ++iterator));
        } else {
            putPhases(phraseUtil.choosePerson());
            iterator = 0;
            person = null;
        }
        return completeExecution();
    }

    public enum Person{
        NASTYA(3, "Настя");

        int count;

        String name;

        Person(int i, String name) {
            count = i;
            this.name = name;
        }

        public boolean hasNext(int iterator){
            return iterator < count;
        }

        public String getName() {
            return name;
        }

    }
}
