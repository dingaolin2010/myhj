package com.hj.activemq;

import com.csg.activemq.model.FileDownloadModel;
import com.csg.exception.AdpException;
import com.csg.protocol.OperationMgr_IStub;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-6-6.
 */

public class HjActiveMQ extends OperationMgr_IStub{

    public static void main(String[] str) throws AdpException {
    	System.out.println("进入main....");
    	
       // com.csg.AdapterDriver.invoke();
        System.out.print("Hello");
    }


    @Override
    public String getAllUserInfos(String s) {
        return null;
    }

    @Override
    public String checkoutUser(String s) {
        return null;
    }

    @Override
    public String getAllFaultsWithTodo(String s) {
        return null;
    }

    @Override
    public String getAllFaults(String s) {
        return null;
    }

    @Override
    public String getFaultDetails(String s) {
        return null;
    }

    @Override
    public String getFaultFlows(String s) {
        return null;
    }

    @Override
    public String submitFaultFlows(String s) {
        return null;
    }

    @Override
    public String getAllExecuteRepairsWithTodo(String s) {
        return null;
    }

    @Override
    public String getAllExecuteRepairs(String s) {
        return null;
    }

    @Override
    public String getExecuteRepairDetails(String s) {
        return null;
    }

    @Override
    public String getExecuteRepairFlows(String s) {
        return null;
    }

    @Override
    public String submitExecuteRepairFlows(String s) {
        return null;
    }

    @Override
    public String getNameAndStringValues(String s) {
        return null;
    }

    @Override
    public String getOperationFlows(String s) {
        return null;
    }

    @Override
    public String getMonthPlanFlows(String s) {
        return null;
    }

    @Override
    public String getAllOperationsWithTodo(String s) {
        return null;
    }

    @Override
    public String getAllOperations(String s) {
        return null;
    }

    @Override
    public String getOperationDetails(String s) {
        return null;
    }

    @Override
    public String submitOperationFlows(String s) {
        return null;
    }

    @Override
    public String submitMonthPlanFlows(String s) {
        return null;
    }

    @Override
    public String getAllMonthPlansWithTodo(String s) {
        return null;
    }

    @Override
    public String getAllMonthPlans(String s) {
        return null;
    }

    @Override
    public String getMonthPlanDetails(String s) {
        return null;
    }

    @Override
    public String uploadFile(String s, String s1, long l, InputStream inputStream, String s2) {
        return null;
    }

    @Override
    public FileDownloadModel downloadFile(String s) {
        return null;
    }

    @Override
    public String getTreeXml(String s) {
        return null;
    }

    @Override
    public String getAllRepairChecksWithTodo(String s) {
        return null;
    }

    @Override
    public String getAllRepairChecks(String s) {
        return null;
    }

    @Override
    public String getRepairCheckDetails(String s) {
        return null;
    }

    @Override
    public String getRepairCheckFlows(String s) {
        return null;
    }

    @Override
    public String submitRepairCheckFlows(String s) {
        return null;
    }

    @Override
    public String getAllPhoneTransactionWithTodo(String s) {
        return null;
    }

    @Override
    public String getAllPhoneTransactions(String s) {
        return null;
    }

    @Override
    public String getPhoneTransactionDetails(String s) {
        return null;
    }

    @Override
    public String getPhoneTransactionFlows(String s) {
        return null;
    }

    @Override
    public String submitPhoneTransactionFlows(String s) {
        return null;
    }

    @Override
    public String changerUserInfo(String s) {
        return null;
    }

    @Override
    public String getAllRepairToursWithTodo(String s) {
        return null;
    }

    @Override
    public String getAllRepairTours(String s) {
        return null;
    }

    @Override
    public String getRepairTourDetails(String s) {
        return null;
    }

    @Override
    public String getRepairTourFlows(String s) {
        return null;
    }

    @Override
    public String submitTourCheckFlows(String s) {
        return null;
    }

    @Override
    public String getAllNotes(String s) {
        return null;
    }

    @Override
    public String getAllBulletins(String s) {
        return null;
    }

    @Override
    public String getAllStandards(String s) {
        return null;
    }

    @Override
    public String getAllCsgMonthPlansToDo(String s) {
        return null;
    }

    @Override
    public String getAllCsgMonthPlans(String s) {
        return null;
    }

    @Override
    public String getStatCsgMonthPlans(String s) {
        return null;
    }

    @Override
    public String getCsgMonthPlansItem(String s) {
        return null;
    }

    @Override
    public String getCsgMonthPlansDetails(String s) {
        return null;
    }

    @Override
    public String getCsgMonthPlansFlows(String s) {
        return null;
    }

    @Override
    public String submitCsgMonthPlansFlows(String s) {
        return null;
    }

    @Override
    public String getAllQuerys(String s) {
        return null;
    }

    @Override
    public String getAllFaultPics(String s) {
        return null;
    }
}
