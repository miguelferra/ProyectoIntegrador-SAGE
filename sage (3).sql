-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: sage
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
  `idactividad` int NOT NULL AUTO_INCREMENT,
  `detalle` varchar(60) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idactividad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades`
--

LOCK TABLES `actividades` WRITE;
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `detalleactividades`
--

LOCK TABLES `detalleactividades` WRITE;
/*!40000 ALTER TABLE `detalleactividades` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleactividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleentregablespaquete`
--

DROP TABLE IF EXISTS `detalleentregablespaquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleentregablespaquete` (
  `tiempoServicio` varchar(10) DEFAULT NULL,
  `cantidadEntregable` int DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `paquetes_idpaquete` int NOT NULL,
  `servicios_idservicio` int DEFAULT NULL,
  `entregables_identregable` int DEFAULT NULL,
  `prueba` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`prueba`),
  KEY `fk_detalleentregablesPaquete_paquetes1_idx` (`paquetes_idpaquete`),
  KEY `fk_detalleentregablesPaquete_servicios1_idx` (`servicios_idservicio`),
  KEY `fk_detalleentregablesPaquete_entregables1_idx` (`entregables_identregable`),
  CONSTRAINT `fk_detalleentregablesPaquete_paquetes1` FOREIGN KEY (`paquetes_idpaquete`) REFERENCES `paquetes` (`idpaquete`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleentregablespaquete`
--

LOCK TABLES `detalleentregablespaquete` WRITE;
/*!40000 ALTER TABLE `detalleentregablespaquete` DISABLE KEYS */;
INSERT INTO `detalleentregablespaquete` VALUES ('1 hora',NULL,NULL,2,2,NULL,1),('Media hora',NULL,NULL,1,1,NULL,2),('',6,NULL,1,NULL,1,3),('',1,NULL,1,NULL,2,4),(NULL,10,NULL,1,NULL,3,5),(NULL,8,NULL,2,NULL,1,6),(NULL,1,NULL,2,NULL,4,7),(NULL,20,NULL,2,NULL,4,8),(NULL,5,NULL,3,NULL,1,9),(NULL,1,NULL,3,NULL,2,10),('1 hora',NULL,NULL,4,2,NULL,11),(NULL,5,NULL,3,NULL,1,12),(NULL,1,NULL,3,NULL,2,13),('1 hora',NULL,NULL,4,2,NULL,14),(NULL,NULL,NULL,4,3,NULL,15),(NULL,5,NULL,4,NULL,1,16),(NULL,1,NULL,4,NULL,5,17),('1 hora',NULL,NULL,5,4,NULL,18),(NULL,8,NULL,5,NULL,1,19),(NULL,1,NULL,5,NULL,5,20),('30 min',NULL,NULL,3,2,NULL,21),('1 hora',NULL,NULL,6,5,NULL,22),(NULL,NULL,NULL,6,3,NULL,23),(NULL,10,NULL,6,NULL,1,24),(NULL,1,NULL,6,NULL,5,25),(NULL,10,NULL,6,NULL,6,26),('30 min',NULL,NULL,7,1,NULL,47),(NULL,10,NULL,7,NULL,3,48),(NULL,1,NULL,7,NULL,2,49),('1 hora',NULL,NULL,8,1,NULL,50),(NULL,2,NULL,8,NULL,4,51),(NULL,20,NULL,8,NULL,3,52),(NULL,6,NULL,7,NULL,NULL,53),(NULL,8,NULL,8,NULL,1,54),('1 hora',NULL,NULL,9,6,NULL,55),(NULL,NULL,NULL,9,7,NULL,56),(NULL,NULL,NULL,9,NULL,7,57),(NULL,12,NULL,9,NULL,1,58),(NULL,30,NULL,9,NULL,3,59),(NULL,2,NULL,9,NULL,4,60),('1 hora',NULL,NULL,10,6,NULL,61),(NULL,12,NULL,10,NULL,1,62),(NULL,1,NULL,10,NULL,8,63);
/*!40000 ALTER TABLE `detalleentregablespaquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleentregablespedido`
--

DROP TABLE IF EXISTS `detalleentregablespedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleentregablespedido` (
  `cantidad` int DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `pedidos_idpedido` int DEFAULT NULL,
  `entregables_identregable` int NOT NULL,
  PRIMARY KEY (`entregables_identregable`),
  KEY `fk_detalleentregables_pedidos1_idx` (`pedidos_idpedido`),
  CONSTRAINT `fk_detalleentregables_pedidos1` FOREIGN KEY (`pedidos_idpedido`) REFERENCES `pedidos` (`idpedido`),
  CONSTRAINT `fk_detalleentregablesPedido_entregables1` FOREIGN KEY (`entregables_identregable`) REFERENCES `entregables` (`identregable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleentregablespedido`
--

LOCK TABLES `detalleentregablespedido` WRITE;
/*!40000 ALTER TABLE `detalleentregablespedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleentregablespedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleservicio`
--

DROP TABLE IF EXISTS `detalleservicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleservicio` (
  `tiempo` varchar(10) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `pedidos_idpedido` int DEFAULT NULL,
  `servicios_idservicio` int NOT NULL,
  PRIMARY KEY (`servicios_idservicio`),
  KEY `fk_detalleservicio_pedidos1_idx` (`pedidos_idpedido`),
  KEY `fk_detalleservicio_servicios1_idx` (`servicios_idservicio`),
  CONSTRAINT `fk_detalleservicio_pedidos1` FOREIGN KEY (`pedidos_idpedido`) REFERENCES `pedidos` (`idpedido`),
  CONSTRAINT `fk_detalleservicio_servicios1` FOREIGN KEY (`servicios_idservicio`) REFERENCES `servicios` (`idservicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleservicio`
--

LOCK TABLES `detalleservicio` WRITE;
/*!40000 ALTER TABLE `detalleservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleservicio` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregables`
--

DROP TABLE IF EXISTS `entregables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregables` (
  `identregable` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `tamaño` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`identregable`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregables`
--

LOCK TABLES `entregables` WRITE;
/*!40000 ALTER TABLE `entregables` DISABLE KEYS */;
INSERT INTO `entregables` VALUES (1,'Fotos impresas y digitales','5x7\'\''),(2,'Ampliacion sencilla','8x10\'\''),(3,'Carteras diferentes tomas','2x3\'\''),(4,'Ampliacion montada y laminada','8x10\'\''),(5,'Lona','1x1.50 mts'),(6,'Cartel','Tabloide'),(7,'Bastidor tipo pintura al óleo','16x24\'\' CANVAS'),(8,'Ampliación (Exhibición dia de boda)','16x20\'\'');
/*!40000 ALTER TABLE `entregables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquetes`
--

DROP TABLE IF EXISTS `paquetes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquetes` (
  `idpaquete` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(70) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`idpaquete`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquetes`
--

LOCK TABLES `paquetes` WRITE;
/*!40000 ALTER TABLE `paquetes` DISABLE KEYS */;
INSERT INTO `paquetes` VALUES (1,'Sesión sencilla en estudio','Sesión Fotografica de 30 min. Varias poses. 1 Vestuario.\r\n',1500),(2,'Sesión sencilla en estudio','Sesión Fotografica de 1 hora. Varias poses. 2 Vestuario.\r\n',1850),(3,'Sesión candidata (No lona)','1 VESTUARIO, 5 FOTOS 5X7  + 1 AMPLIACION 8X10',1500),(4,'Sesión candidata estudio (Con lona)','Sesión fotográfica en estudio Duración 1 hora, Varias poses. 2 vestuar',1999),(5,'Sesión candidata exterior (Con lona)','Sesión fotográfica Exterior jardín urbana Duración 1 hora, Varias pose',2450),(6,'Sesión candidata exterior + estudio (Con lona','Sesión fotográfica Exterior jardín urbana Duración 1 hora, Varias pose',2999),(7,'Sesión en estudio (-pareja-)','Sesión fotográfica Duración 30 min. Varias poses juntos, 1 vestuario.',1550),(8,'Sesión exterior (-pareja-)','Sesión fotográfica Duración 1hora, Varias poses juntos, 2 vestuario.',2000),(9,'SESION SAVE THE DATE “COMPROMISO” temática','Esta sesión está diseñada para reafirmar el compromiso que se tienen l',3750),(10,'Sesión temática pareja','(“Save the date”) :\r\nSESIÓN COMPROMISO TEMÁTICA :\r\nEsta sesión está di',3500),(11,'Sesión quince años hombre 1Hr.',' 1 hr. de sesión (traje + fashion) estudio luz profesional',2250),(12,'Sesión quince años hombre 1 y 1/2Hrs.','1.30 hr. de sesión (traje + fashion) estudio luz profesional',3550),(13,'Sesión boda en interior estudio','Sesión de Novios, duración 1 hr. en Interior Estudio AF , Servicio de ',3000),(14,'Sesión boda en jardin exterior','Sesión de Novios, duración 1 hr. 30 min. en locación exterior ó Interi',4000);
/*!40000 ALTER TABLE `paquetes` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios` (
  `idservicio` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(25) DEFAULT NULL,
  `lugar` varchar(45) DEFAULT NULL,
  `detalle` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`idservicio`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (1,'sesion fotografica','Estudio','Varias Poses. 1 Vestuario'),(2,' sesion fotografica','Estudio','Varias Poses. 2 Vestuarios'),(3,'Asesoramiento de imagen',NULL,'Asesoramiento de imagen'),(4,'Sesion exterior','Jardín urbana','Sesion Fotográfica exterior jardín urbana 2 vestuarios'),(5,'Sesion exterior','Jardín urbano','Sesion Fotográfica en jardín exterior 3 vestuarios'),(6,'Sesion fotografica elegir','A elegir','Sesion fotografica en estudio o locacion a elegir'),(7,'Servicio Exhibición','Evento','Servicio de Exhibición en evento de tu ampliación');
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-22 23:52:31
