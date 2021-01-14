import java.util.*;

public class DKOrgChart{

    class OrgChart{

        Map<String, Employee> rootMap = new LinkedHashMap<>();

        public void add( String id, String name, String managerId ){
            if( ! rootMap.containsKey( id ) ){
                Employee newEmployee = new Employee( id, name, managerId );
                rootMap.put( id, newEmployee );
                if( managerId != null && rootMap.containsKey( managerId ) ){
                    rootMap.get( managerId ).employees.add( id );
                }
            }
        }

        public void print(){
            Set<String> printedIds = new HashSet<>();
            rootMap.forEach( ( key, value ) -> {
                if( ! rootMap.containsKey( value.managerId ) ){
                    print( key, 0, printedIds );
                }
            } );
        }

        private void print( String eid, int depth, Set<String> printedIds ){
            if( ! printedIds.contains( eid ) && rootMap.containsKey( eid ) ){
                Employee employee = rootMap.get( eid );
                StringBuilder sb = new StringBuilder( "" );
                for( int i = 0; i < depth; i++ ){
                    sb.append( "  " );
                }
                System.out.println( sb.toString() + employee.name + " [" + employee.id + "]" );
                printedIds.add( employee.id );
                for( String cid: employee.employees ){
                    print( cid, depth + 1, printedIds );
                }
            }
        }

        public void remove( String employeeId ){
            if( rootMap.containsKey( employeeId ) ){
                Employee removed = rootMap.remove( employeeId );
                if( rootMap.containsKey( removed.managerId ) ){
                    Employee newMngr = rootMap.get( removed.managerId );
                    for( String cid: removed.employees ){
                        Employee employee = rootMap.get( cid );
                        employee.managerId = removed.managerId;
                        newMngr.employees.add( cid );
                    }
                }
            }
        }

        public void move( String employeeId, String newManagerId ){
            if( rootMap.containsKey( employeeId ) ){
                Employee moving = rootMap.get( employeeId );
                if( rootMap.containsKey( moving.managerId ) ){
                    Employee m = rootMap.get( moving.managerId );
                    m.employees.remove( moving.id );
                }
                if( rootMap.containsKey( newManagerId ) ){
                    moving.managerId = newManagerId;
                    rootMap.get( newManagerId ).employees.add( moving.id );
                }

            }
        }

        public int count( String employeeId ){
            return countTotal( employeeId ) - 1;
        }

        private int countTotal( String employeeId ){
            if( rootMap.containsKey( employeeId ) ){
                int rt = 1;
                Employee e = rootMap.get( employeeId );
                for( String cid: e.employees ){
                    rt += countTotal( cid );
                }
                return rt;
            }
            throw new RuntimeException( "No exist?!" );
        }
    }

    class Employee{

        public Employee( String id, String name, String managerId ){
            this.id = id;
            this.name = name;
            this.managerId = managerId;
        }

        String id;
        String name;
        String managerId;
        List<String> employees = new ArrayList<>();

        @Override
        public String toString(){
            return "Employee{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", managerId='" + managerId + '\'' +
                    ", employees=" + employees +
                    '}';
        }

    }


}
