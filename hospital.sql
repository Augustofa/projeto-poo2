-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 29, 2023 at 04:22 PM
-- Server version: 5.7.25
-- PHP Version: 7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `consultas`
--

CREATE TABLE `consultas` (
  `nome_medico` varchar(150) NOT NULL,
  `crm_medico` varchar(30) NOT NULL,
  `nome_paciente` varchar(150) NOT NULL,
  `cpf_paciente` varchar(30) NOT NULL,
  `data` date NOT NULL,
  `horario` time NOT NULL,
  `receitas` text NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `consultas`
--

INSERT INTO `consultas` (`nome_medico`, `crm_medico`, `nome_paciente`, `cpf_paciente`, `data`, `horario`, `receitas`, `id`) VALUES
('Vinícius Guerra Machado', '581028/SP ', 'Augusto Fernandes', '124.699.230-53', '2021-07-12', '15:45:00', 'Dipirona 5 mg - 10 vezes ao dia', 2),
('Vinicius Guerra Machado', '582049/SP ', 'Carla Moraes de Oliveira', '123.995.258-29', '2022-11-23', '12:30:00', 'Nenhuma', 5);

-- --------------------------------------------------------

--
-- Table structure for table `medicos`
--

CREATE TABLE `medicos` (
  `nome` varchar(150) NOT NULL,
  `telefone` varchar(30) NOT NULL,
  `endereco` varchar(150) NOT NULL,
  `num_identificador` varchar(30) NOT NULL,
  `crm` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `convenios` varchar(150) NOT NULL,
  `especialidade` varchar(50) NOT NULL,
  `formacoes` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `medicos`
--

INSERT INTO `medicos` (`nome`, `telefone`, `endereco`, `num_identificador`, `crm`, `email`, `convenios`, `especialidade`, `formacoes`) VALUES
('Vinícius Guerra Machado', '38329849', 'Rua Cura da Dores 190', '85.013.752/9458-29', '299348/SP ', 'vinnyguerra@gmail.com', '[Bradesco, Hapvida]', 'Fisioterapia', 'Aromoterapia e Equinoterapia'),
('Vagner Moura', '3831-5899', 'Rua Teófilo Otoni', '18.609.646/8123-77', '873409/MG ', 'drvaltermoura@gmail.com', '[Unimed, Benevida, Amil Assistencia]', 'Cardiologia', 'Pediatria\nCuidado Paleativo');

-- --------------------------------------------------------

--
-- Table structure for table `pacientes`
--

CREATE TABLE `pacientes` (
  `nome` varchar(150) NOT NULL,
  `data_nascimento` date NOT NULL,
  `sexo` varchar(2) NOT NULL,
  `tipo_sanguineo` varchar(4) NOT NULL,
  `endereco` varchar(150) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `convenio` varchar(150) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `historico` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pacientes`
--

INSERT INTO `pacientes` (`nome`, `data_nascimento`, `sexo`, `tipo_sanguineo`, `endereco`, `cpf`, `convenio`, `telefone`, `historico`) VALUES
('Augusto Fernandes', '2002-01-08', 'M', 'A+', 'Avenida Rui Barbosa 590', '106.999.879-07', 'Unimed', '38315566', 'Alergias diversas'),
('Breno da Silva', '1998-01-02', 'M', 'A-', 'Avenida Morumbi 420', '321.412.521-31', 'Amil Assistência', '74289304', 'Nada'),
('Carla Moraes de Oliveira', '2014-04-12', 'F', 'O+', 'Rua do Tapajós 1020', '509.653.219-43', 'Bradesco', '999242342', 'Cirurgia de apêndicite');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`crm`);

--
-- Indexes for table `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`cpf`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultas`
--
ALTER TABLE `consultas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
