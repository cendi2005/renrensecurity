//package io.renren.encryption;
//
///**
// * Created by admin on 2017/7/20.
// */
//import oshi.SystemInfo;
//import oshi.hardware.CentralProcessor;
//import oshi.hardware.HardwareAbstractionLayer;
//import oshi.software.os.OperatingSystem;
//
//import java.util.UUID;
//
///**
// *
// * 机器的硬件信息
// * https://github.com/oshi/oshi
// * */
//public class ComputerIdentifierUtil
//{
//    public static String generateLicenseKey() throws Exception
//    {
////        SystemInfo systemInfo = new SystemInfo();
////        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
////        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
////        CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
////
////        String vendor = operatingSystem.getManufacturer();
////        System.out.println("vendor:"+vendor);
////        String processorSerialNumber = centralProcessor.getSystemSerialNumber();
////        System.out.println("processorSerialNumber:"+processorSerialNumber);
////        String processorIdentifier = centralProcessor.getIdentifier();
////        System.out.println("processorIdentifier:"+processorIdentifier);
////        int processors = centralProcessor.getLogicalProcessorCount();
////        System.out.println("processors:"+processors);
////
////        String delimiter = "#";
////
////        return vendor +
////                delimiter +
////                processorSerialNumber +
////                delimiter +
////                processorIdentifier +
////                delimiter +
////                processors;
////    }
//
//    public static void main(String[] arguments) throws Exception
//    {
////        String identifier = UUID.randomUUID()+generateLicenseKey().trim();
////        System.out.println("identifier...."+identifier.trim());
//    }
//}
