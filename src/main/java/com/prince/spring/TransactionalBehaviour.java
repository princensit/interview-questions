package com.prince.spring;

/**
 * @author Prince Raj
 */
public class TransactionalBehaviour {
//    // set is either using xml Or annotation
//    DataSourceTransactionManager manager=new DataSourceTransactionManager();
//    SimpleTransactionStatus status=new SimpleTransactionStatus();
//    ;
//
//
//    public void beginTransaction()
//    {
//        DefaultTransactionDefinition Def = new DefaultTransactionDefinition();
//        // overwrite default PROPAGATION_REQUIRED and ISOLATION_DEFAULT
//        // set is either using xml Or annotation
//        manager.setPropagationBehavior(XX);
//        manager.setIsolationLevelName(XX);
//
//        status = manager.getTransaction(Def);
//
//    }
//
//    public void commitTransaction()
//    {
//
//
//        if(status.isCompleted()){
//            manager.commit(status);
//        }
//    }
//
//    public void rollbackTransaction()
//    {
//
//        if(!status.isCompleted()){
//            manager.rollback(status);
//        }
//    }
//    Main method{
//        beginTransaction()
//        M1();
//        If error(){
//            rollbackTransaction()
//        }
//        commitTransaction();
//    }

}
