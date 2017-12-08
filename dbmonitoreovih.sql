-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-12-2017 a las 01:42:05
-- Versión del servidor: 10.1.22-MariaDB
-- Versión de PHP: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbmonitoreovih`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `ID_COMPRA` int(11) NOT NULL,
  `PERIODO_COMPRA` varchar(15) NOT NULL,
  `ID_PLANIFICACION` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`ID_COMPRA`, `PERIODO_COMPRA`, `ID_PLANIFICACION`) VALUES
(1, 'loco', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribucion`
--

CREATE TABLE `distribucion` (
  `ID_DISTRIBUCION` int(11) NOT NULL,
  `PERIODO_DISTRIBUCION` varchar(15) NOT NULL,
  `CANTIDAD_DISTRIBUIDA` int(11) NOT NULL,
  `CANTIDAD_NO_DISTRIBUIDA` int(11) NOT NULL,
  `ID_PLANIFICACION` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `existencialaboratorio`
--

CREATE TABLE `existencialaboratorio` (
  `TIPOFABRICACION` varchar(100) NOT NULL,
  `USO` varchar(100) NOT NULL,
  `FORMAPRESENTACION` varchar(100) NOT NULL,
  `ID_LABORATORIO` int(11) NOT NULL,
  `ID_EXIST` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `existencialaboratorio`
--

INSERT INTO `existencialaboratorio` (`TIPOFABRICACION`, `USO`, `FORMAPRESENTACION`, `ID_LABORATORIO`, `ID_EXIST`) VALUES
('4', '4', '4', 4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `existenciamaterialmedico`
--

CREATE TABLE `existenciamaterialmedico` (
  `ID_EXIST` int(11) NOT NULL,
  `CODIGO_EXIST` int(11) NOT NULL,
  `FECHACONTEO` varchar(15) NOT NULL,
  `CANTIDADEXISTENCIA` int(11) NOT NULL,
  `CANTIDADFALTANTE` int(11) NOT NULL,
  `OBSERVACION` varchar(256) NOT NULL,
  `NOMBRE_MATERIAL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `existenciamaterialmedico`
--

INSERT INTO `existenciamaterialmedico` (`ID_EXIST`, `CODIGO_EXIST`, `FECHACONTEO`, `CANTIDADEXISTENCIA`, `CANTIDADFALTANTE`, `OBSERVACION`, `NOMBRE_MATERIAL`) VALUES
(4, 4, '2017-12-07', 4, 4, '4', '4'),
(5, 5, '2017-12-07', 5, 5, 'hay cinco', 'cinco'),
(12, 12, '2017-11-27', 5, 2, 'No hay observaciones', ''),
(15, 15, '2017-11-27', 1, 5, 'NO hay', ''),
(17, 17, '2017-11-27', 6, 6, 'No hay', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `existenciatar`
--

CREATE TABLE `existenciatar` (
  `NOMBRECOMERCIAL` varchar(256) NOT NULL,
  `ACCIONFARMACOLOGICA` varchar(256) NOT NULL,
  `ID_TAR` int(11) NOT NULL,
  `ID_EXIST` int(11) DEFAULT NULL,
  `ID_LINEA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `existenciatar`
--

INSERT INTO `existenciatar` (`NOMBRECOMERCIAL`, `ACCIONFARMACOLOGICA`, `ID_TAR`, `ID_EXIST`, `ID_LINEA`) VALUES
('Cinco', 'Cinquear', 5, 5, 2),
('Paracetamol', 'Beber cada 3 horas', 12, 12, 2),
('Tratamiento antiretroviral', 'Beber con aguita', 15, 15, 1),
('Nombre medio random', 'Almacenar en la refri', 17, 17, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_de_nivel`
--

CREATE TABLE `linea_de_nivel` (
  `ID_LINEA` int(11) NOT NULL,
  `NOMBRE_LINEA` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `linea_de_nivel`
--

INSERT INTO `linea_de_nivel` (`ID_LINEA`, `NOMBRE_LINEA`, `DESCRIPCION`) VALUES
(1, 'Linea 1', 'esta es la linea 1'),
(2, 'Linea 2', 'esta es la linea 2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `ID_PERSONAL` int(11) NOT NULL,
  `AREA` varchar(50) NOT NULL,
  `CANTIDAD_PERSONAL_DISPONIBLE` int(11) NOT NULL,
  `PORCENTAJE_ROTACION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`ID_PERSONAL`, `AREA`, `CANTIDAD_PERSONAL_DISPONIBLE`, `PORCENTAJE_ROTACION`) VALUES
(1, 'area 1', 100, 1),
(2, 'area 2', 2, 2),
(3, 'area 3', 23, 23),
(4, 'area 45', 45, 45),
(5, '5', 5, 5),
(6, '6', 6, 6),
(7, '7', 7, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planificacion`
--

CREATE TABLE `planificacion` (
  `ID_PLANIFICACION` int(11) NOT NULL,
  `FECHA_INICIO` varchar(15) NOT NULL,
  `FECHA_FIN` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `planificacion`
--

INSERT INTO `planificacion` (`ID_PLANIFICACION`, `FECHA_INICIO`, `FECHA_FIN`) VALUES
(3, '2017-12-06', '2017-12-07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presupuesto`
--

CREATE TABLE `presupuesto` (
  `ID_PRESUPUESTO` int(11) NOT NULL,
  `CANTIDAD_DISPONIBLE` int(11) NOT NULL,
  `CANTIDAD_FALTANTE` int(11) NOT NULL,
  `PERIODO_PRESUPUESTO` varchar(15) NOT NULL,
  `ID_PLANIFICACION` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`ID_COMPRA`),
  ADD UNIQUE KEY `ID_COMPRA_UNIQUE` (`ID_PLANIFICACION`);

--
-- Indices de la tabla `distribucion`
--
ALTER TABLE `distribucion`
  ADD PRIMARY KEY (`ID_DISTRIBUCION`),
  ADD UNIQUE KEY `ID_COMPRA_UNIQUE_3` (`ID_PLANIFICACION`);

--
-- Indices de la tabla `existencialaboratorio`
--
ALTER TABLE `existencialaboratorio`
  ADD PRIMARY KEY (`ID_LABORATORIO`),
  ADD UNIQUE KEY `ID_EXIST_UNIQUE_2` (`ID_EXIST`);

--
-- Indices de la tabla `existenciamaterialmedico`
--
ALTER TABLE `existenciamaterialmedico`
  ADD PRIMARY KEY (`ID_EXIST`);

--
-- Indices de la tabla `existenciatar`
--
ALTER TABLE `existenciatar`
  ADD PRIMARY KEY (`ID_TAR`),
  ADD UNIQUE KEY `ID_EXIST_UNIQUE` (`ID_EXIST`),
  ADD KEY `FK_POSEE` (`ID_LINEA`);

--
-- Indices de la tabla `linea_de_nivel`
--
ALTER TABLE `linea_de_nivel`
  ADD PRIMARY KEY (`ID_LINEA`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`ID_PERSONAL`);

--
-- Indices de la tabla `planificacion`
--
ALTER TABLE `planificacion`
  ADD PRIMARY KEY (`ID_PLANIFICACION`);

--
-- Indices de la tabla `presupuesto`
--
ALTER TABLE `presupuesto`
  ADD PRIMARY KEY (`ID_PRESUPUESTO`),
  ADD UNIQUE KEY `ID_PLANIFICACION_UNIQUE_2` (`ID_PLANIFICACION`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `ID_COMPRA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FK_ES_UNA` FOREIGN KEY (`ID_PLANIFICACION`) REFERENCES `planificacion` (`ID_PLANIFICACION`);

--
-- Filtros para la tabla `distribucion`
--
ALTER TABLE `distribucion`
  ADD CONSTRAINT `FK_ES_UNA_3` FOREIGN KEY (`ID_PLANIFICACION`) REFERENCES `planificacion` (`ID_PLANIFICACION`);

--
-- Filtros para la tabla `existencialaboratorio`
--
ALTER TABLE `existencialaboratorio`
  ADD CONSTRAINT `FK_ES_UN` FOREIGN KEY (`ID_EXIST`) REFERENCES `existenciamaterialmedico` (`ID_EXIST`);

--
-- Filtros para la tabla `existenciatar`
--
ALTER TABLE `existenciatar`
  ADD CONSTRAINT `FK_ES_UN_2` FOREIGN KEY (`ID_EXIST`) REFERENCES `existenciamaterialmedico` (`ID_EXIST`),
  ADD CONSTRAINT `FK_POSEE` FOREIGN KEY (`ID_LINEA`) REFERENCES `linea_de_nivel` (`ID_LINEA`);

--
-- Filtros para la tabla `presupuesto`
--
ALTER TABLE `presupuesto`
  ADD CONSTRAINT `FK_ES_UNA_2` FOREIGN KEY (`ID_PLANIFICACION`) REFERENCES `planificacion` (`ID_PLANIFICACION`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
