-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-12-2017 a las 01:43:28
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
-- Base de datos: `dbprotocolos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `protocolos`
--

CREATE TABLE `protocolos` (
  `ID_PROTOCOLOS` int(11) NOT NULL,
  `PROTOCOLO_ATENCION` varchar(50) NOT NULL,
  `NORMA_TECNICA` varchar(100) NOT NULL,
  `INSTRUCTIVO` varchar(256) NOT NULL,
  `BASE_LEGAL` varchar(100) NOT NULL,
  `CANTIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `protocolos`
--

INSERT INTO `protocolos` (`ID_PROTOCOLOS`, `PROTOCOLO_ATENCION`, `NORMA_TECNICA`, `INSTRUCTIVO`, `BASE_LEGAL`, `CANTIDAD`) VALUES
(3, 'TAR_TIPO_1', 'Este medicamento es importante', 'Se suministra cada 12 horas', 'Basados en el Art 12 de la Constitucion', 30),
(4, 'TAR_TIPO_2', 'Este medicamento es super importante', 'Suministrado cada 2 horas', 'Articulo 32 de la constiutucion', 100),
(5, 'LAB_TIPO_1', 'No es muy importante', 'Debe de existir lo minimo necesario', 'Art 45 del codigo de trabajo', 10),
(6, 'TAR_TIPO_3', 'Norma', 'Instructivo', 'Base legal', 200);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `protocolos`
--
ALTER TABLE `protocolos`
  ADD PRIMARY KEY (`ID_PROTOCOLOS`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
