-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-02-17 21:14:39
-- 服务器版本： 5.7.26
-- PHP 版本： 5.6.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `exampro`
--

-- --------------------------------------------------------

--
-- 表的结构 `function`
--

CREATE TABLE `function` (
  `funid` int(11) NOT NULL,
  `funname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `funurl` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `funpid` int(11) NOT NULL DEFAULT '0',
  `funstate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `function`
--

INSERT INTO `function` (`funid`, `funname`, `funurl`, `funpid`, `funstate`) VALUES
(1, '系统功能', NULL, 0, 1),
(2, '用户管理', 'UserServlet?obj=selectall&pageNo=1', 1, 1),
(14, '系统功能管理', 'FunctionServlet?obj=selectall&pageNo=1', 1, 1),
(15, '角色管理', 'RoleServlet?obj=selectall&pageNo=1', 1, 1),
(17, '试题管理', '', 0, 1),
(18, '题目管理', 'SubjectServlet?obj=selectall&pageNo=1', 17, 1),
(19, '试卷管理', 'PaperServlet?obj=selectall&pageNo=1', 17, 1);

-- --------------------------------------------------------

--
-- 表的结构 `paper`
--

CREATE TABLE `paper` (
  `pid` int(11) NOT NULL,
  `pname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `paper`
--

INSERT INTO `paper` (`pid`, `pname`, `sid`) VALUES
(1, 'JAVA测试卷', 1),
(2, 'JAVA测试卷', 6),
(3, '网络技术', 2),
(4, '网络技术', 1),
(5, '计算机网络', 6),
(6, '高数', 9),
(7, '高数', 6),
(8, '高数', 4),
(9, '高数', 8),
(34, '高数', 4),
(35, '高数', 8),
(36, '高数', 9),
(37, '高数', 3),
(41, '牛逼', 8),
(42, '牛逼', 4),
(44, '牛逼', 3),
(45, '牛逼', 6),
(47, '哈哈', 8),
(48, '哈哈', 6),
(50, '666', 9),
(51, '666', 3),
(52, '666', 6),
(53, 'ccc', 6),
(54, 'ccc', 9),
(55, 'ccc', 4),
(56, '无敌了', 3),
(57, '无敌了', 9),
(58, '无敌了', 4),
(59, '无敌了', 8),
(60, '无敌了', 6);

-- --------------------------------------------------------

--
-- 表的结构 `premission`
--

CREATE TABLE `premission` (
  `rrid` int(11) NOT NULL,
  `funid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `premission`
--

INSERT INTO `premission` (`rrid`, `funid`, `roleid`) VALUES
(72, 1, 1),
(73, 2, 1),
(74, 14, 1),
(75, 15, 1),
(76, 17, 1),
(77, 18, 1),
(78, 19, 1);

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE `role` (
  `roleid` int(11) NOT NULL,
  `rolename` varchar(20) DEFAULT '',
  `rolestate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`roleid`, `rolename`, `rolestate`) VALUES
(1, '超级管理员', 1),
(2, '试题管理员', 0),
(3, '学生', 1),
(4, '后勤管理员', 1),
(5, '起飞', 1);

-- --------------------------------------------------------

--
-- 表的结构 `studentpaper`
--

CREATE TABLE `studentpaper` (
  `id` int(11) NOT NULL,
  `spid` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `userid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `studentkey` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `studentstate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `pname` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `studentpaper`
--

INSERT INTO `studentpaper` (`id`, `spid`, `userid`, `sid`, `studentkey`, `studentstate`, `pname`) VALUES
(25, '1581822096182', 3, 9, 'A', '1', '666'),
(26, '1581822096182', 3, 3, 'A', '0', '666'),
(27, '1581822096182', 3, 6, 'B', '0', '666'),
(28, '1581822921017', 3, 9, 'A', '1', '666'),
(29, '1581822921017', 3, 6, 'C', '0', '666'),
(30, '1581822921017', 3, 3, 'B', '0', '666'),
(31, '1581822956360', 3, 9, 'A', '1', '666'),
(32, '1581822956360', 3, 3, 'B', '0', '666'),
(33, '1581822956360', 3, 6, 'C', '0', '666'),
(34, '1581843075649', 3, 3, 'D', '0', '666'),
(35, '1581843075649', 3, 6, 'D', '0', '666'),
(36, '1581843075649', 3, 9, 'A', '1', '666'),
(37, '1581930203113', 3, 6, 'D', '0', '计算机网络');

-- --------------------------------------------------------

--
-- 表的结构 `subject`
--

CREATE TABLE `subject` (
  `sid` int(11) NOT NULL,
  `scontent` varchar(150) NOT NULL,
  `sa` varchar(100) NOT NULL,
  `sb` varchar(100) NOT NULL,
  `sc` varchar(100) NOT NULL,
  `sd` varchar(100) NOT NULL,
  `skey` varchar(10) NOT NULL,
  `sstate` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `subject`
--

INSERT INTO `subject` (`sid`, `scontent`, `sa`, `sb`, `sc`, `sd`, `skey`, `sstate`) VALUES
(1, '1+22', '5', '4', '3', '333', 'C', 0),
(2, '1+2', '5', '4', '3', '2', 'C', 0),
(3, '1+3', '5', '4', '3', '2', 'C', 1),
(4, '1+4', '5', '4', '3', '2', 'A', 1),
(6, '1+6', 'a', 'b', 'c', 'd', 'A', 1),
(8, '1+8', '1', '1', '1', '1', 'A', 1),
(9, '1+9', '1', '1', '1', '1', 'A', 1);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `userpwd` varchar(20) NOT NULL,
  `usertruename` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userid`, `roleid`, `username`, `userpwd`, `usertruename`) VALUES
(1, 1, '钟南山', 'admin', '南山'),
(2, 2, 'jackma', 'alibaba', '马云'),
(3, 3, 'huxiang', '123456', '胡祥'),
(4, 1, 'XSS', '123', '111'),
(9, 3, 'admin', 'admin', 'admin'),
(10, 3, '222', '222', '222'),
(11, 1, '赵瑞', '123', '1'),
(12, 3, 'hhh', '666', 'hhh'),
(13, 4, 'houqin', 'houqin', '后勤');

--
-- 转储表的索引
--

--
-- 表的索引 `function`
--
ALTER TABLE `function`
  ADD PRIMARY KEY (`funid`),
  ADD UNIQUE KEY `funname` (`funname`);

--
-- 表的索引 `paper`
--
ALTER TABLE `paper`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `sid` (`sid`);

--
-- 表的索引 `premission`
--
ALTER TABLE `premission`
  ADD PRIMARY KEY (`rrid`),
  ADD KEY `功能ID` (`funid`),
  ADD KEY `角色` (`roleid`);

--
-- 表的索引 `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleid`),
  ADD UNIQUE KEY `rolename` (`rolename`);

--
-- 表的索引 `studentpaper`
--
ALTER TABLE `studentpaper`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`sid`),
  ADD UNIQUE KEY `scontent` (`scontent`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `user_username_uindex` (`username`),
  ADD KEY `roleid` (`roleid`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `function`
--
ALTER TABLE `function`
  MODIFY `funid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- 使用表AUTO_INCREMENT `paper`
--
ALTER TABLE `paper`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- 使用表AUTO_INCREMENT `premission`
--
ALTER TABLE `premission`
  MODIFY `rrid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- 使用表AUTO_INCREMENT `role`
--
ALTER TABLE `role`
  MODIFY `roleid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用表AUTO_INCREMENT `studentpaper`
--
ALTER TABLE `studentpaper`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- 使用表AUTO_INCREMENT `subject`
--
ALTER TABLE `subject`
  MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- 限制导出的表
--

--
-- 限制表 `paper`
--
ALTER TABLE `paper`
  ADD CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `subject` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `premission`
--
ALTER TABLE `premission`
  ADD CONSTRAINT `功能ID` FOREIGN KEY (`funid`) REFERENCES `function` (`funid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `角色` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`);

--
-- 限制表 `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `roleid` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
