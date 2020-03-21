-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: basedatos_sage
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividades`
--

DROP TABLE IF EXISTS `actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividades` (
  `idactividad` int NOT NULL,
  `detalle` varchar(60) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idactividad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `telefono` varchar(24) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `direccion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleactividades`
--

DROP TABLE IF EXISTS `detalleactividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleactividades` (
  `fecha` int NOT NULL,
  `actividades_idactividad` int NOT NULL,
  `pedidos_idpedido` int NOT NULL,
  `empleados_RFC` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`actividades_idactividad`,`pedidos_idpedido`),
  KEY `fk_detalleactividad_actividades1_idx` (`actividades_idactividad`),
  KEY `fk_detalleactividad_pedidos1_idx` (`pedidos_idpedido`),
  KEY `fk_detalleactividades_empleados1_idx` (`empleados_RFC`),
  CONSTRAINT `fk_detalleactividad_actividades1` FOREIGN KEY (`actividades_idactividad`) REFERENCES `actividades` (`idactividad`),
  CONSTRAINT `fk_detalleactividad_pedidos1` FOREIGN KEY (`pedidos_idpedido`) REFERENCES `pedidos` (`idpedido`),
  CONSTRAINT `fk_detalleactividades_empleados1` FOREIGN KEY (`empleados_RFC`) REFERENCES `empleados` (`RFC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleentregables`
--

DROP TABLE IF EXISTS `detalleentregables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleentregables` (
  `cantidad` int DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `pedidos_idpedido` int DEFAULT NULL,
  `entregables_identregable` varchar(15) NOT NULL,
  `paquetes_idpaquete` int NOT NULL,
  PRIMARY KEY (`entregables_identregable`,`paquetes_idpaquete`),
  KEY `fk_detalleentregables_pedidos1_idx` (`pedidos_idpedido`),
  KEY `fk_detalleentregables_paquetes1_idx` (`paquetes_idpaquete`),
  CONSTRAINT `fk_detalleentregables_entregables1` FOREIGN KEY (`entregables_identregable`) REFERENCES `entregables` (`identregable`),
  CONSTRAINT `fk_detalleentregables_paquetes1` FOREIGN KEY (`paquetes_idpaquete`) REFERENCES `paquetes` (`idpaquete`),
  CONSTRAINT `fk_detalleentregables_pedidos1` FOREIGN KEY (`pedidos_idpedido`) REFERENCES `pedidos` (`idpedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleservicio`
--

DROP TABLE IF EXISTS `detalleservicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleservicio` (
  `direccion` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `pedidos_idpedido` int DEFAULT NULL,
  `paquetes_idpaquete` int NOT NULL,
  `servicios_idservicio` int NOT NULL,
  PRIMARY KEY (`paquetes_idpaquete`,`servicios_idservicio`),
  KEY `fk_detalleservicio_pedidos1_idx` (`pedidos_idpedido`),
  KEY `fk_detalleservicio_servicios1_idx` (`servicios_idservicio`),
  CONSTRAINT `fk_detalleservicio_paquetes1` FOREIGN KEY (`paquetes_idpaquete`) REFERENCES `paquetes` (`idpaquete`),
  CONSTRAINT `fk_detalleservicio_pedidos1` FOREIGN KEY (`pedidos_idpedido`) REFERENCES `pedidos` (`idpedido`),
  CONSTRAINT `fk_detalleservicio_servicios1` FOREIGN KEY (`servicios_idservicio`) REFERENCES `servicios` (`idservicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `RFC` varchar(13) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(50) NOT NULL,
  `rol` varchar(25) NOT NULL,
  `telefono` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`RFC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entregables`
--

DROP TABLE IF EXISTS `entregables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregables` (
  `identregable` varchar(15) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `tamaño` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`identregable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paquetes`
--

DROP TABLE IF EXISTS `paquetes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquetes` (
  `idpaquete` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(70) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`idpaquete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `idpedido` int NOT NULL AUTO_INCREMENT,
  `fechaPedido` datetime NOT NULL,
  `fechaRequerida` datetime DEFAULT NULL,
  `precio` double NOT NULL,
  `promocion` varchar(45) NOT NULL,
  `notas` varchar(60) DEFAULT NULL,
  `paquetes_idpaquete` int NOT NULL,
  `clientes_idcliente` int NOT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `fk_pedidos_paquetes1_idx` (`paquetes_idpaquete`),
  KEY `fk_pedidos_clientes1_idx` (`clientes_idcliente`),
  CONSTRAINT `fk_pedidos_clientes1` FOREIGN KEY (`clientes_idcliente`) REFERENCES `clientes` (`idcliente`),
  CONSTRAINT `fk_pedidos_paquetes1` FOREIGN KEY (`paquetes_idpaquete`) REFERENCES `paquetes` (`idpaquete`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios` (
  `idservicio` int NOT NULL,
  `tipo` varchar(10) DEFAULT NULL,
  `tiempo` varchar(5) DEFAULT NULL,
  `lugar` varchar(45) DEFAULT NULL,
  `detalle` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`idservicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-21  2:14:56
