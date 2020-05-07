/*
 Navicat Premium Data Transfer

 Source Server         : 31
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.2.31:3306
 Source Schema         : fkh_test_scf

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 23/04/2020 10:22:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_bill
-- ----------------------------
CREATE TABLE `account_bill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `repay_status` tinyint(255) DEFAULT NULL COMMENT '还款状态',
  `due_status` tinyint(2) DEFAULT 0 COMMENT '到期状态',
  `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账单标号',
  `bill_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '账单金额',
  `repay_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '还款金额',
  `remain_repay_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '剩余待还',
  `waybill_count` int(20) DEFAULT 0 COMMENT '运单个数',
  `credit_apply_id` bigint(20) DEFAULT NULL COMMENT '用信申请Id',
  `subject_claims_order_id` bigint(20) DEFAULT NULL COMMENT '关联标的债权订单id',
  `bill_date` date DEFAULT NULL COMMENT '出账日期 放款日期',
  `repay_date` date DEFAULT NULL COMMENT '还款期限',
  `owner_id` bigint(20) DEFAULT 0 COMMENT '创建人所属主账号',
  `create_user_id` bigint(20) DEFAULT 0 COMMENT '创建人id',
  `manage_rate` decimal(10, 8) DEFAULT 0.00000000 COMMENT '管理费率',
  `interest_rate` decimal(10, 8) DEFAULT 0.00000000 COMMENT '利率：年化利率',
  `payment_days` tinyint(2) DEFAULT 0 COMMENT '账期：天数',
  `grace_days` tinyint(2) DEFAULT 0 COMMENT '宽限期：天数',
  `grace_rate` decimal(10, 8) DEFAULT 0.00000000 COMMENT '宽限期利率：用于计算宽限期天数的利息',
  `overdue_rate` decimal(10, 8) DEFAULT 0.00000000 COMMENT '逾期利率：用于计算逾期天数的利息',
  `counterparty_id` bigint(20) DEFAULT NULL COMMENT '交易对手Id',
  `fact_repay_date` datetime(0) DEFAULT NULL COMMENT '实际还款日',
  `grace_date` date DEFAULT NULL COMMENT '宽限日',
  `grace_rate_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '宽限期利息',
  `interest_rate_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '正常利息',
  `overdue_rate_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '超期利息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_notify_sms_log
-- ----------------------------
CREATE TABLE `account_notify_sms_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `mobile_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提醒电话号码',
  `company_borrower_id` bigint(20) DEFAULT NULL COMMENT '借款方Id',
  `company_capital_id` int(11) DEFAULT NULL COMMENT '资方id',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `send_status` tinyint(1) DEFAULT NULL COMMENT '发送状态 0 发送失败 1发送成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信提醒记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_function
-- ----------------------------
CREATE TABLE `auth_function`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级Id',
  `func_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '功能定位key',
  `func_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '功能名称',
  `func_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '功能描述',
  `func_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '功能地址',
  `func_type` tinyint(4) NOT NULL COMMENT '功能类型：1.菜单，2.权限',
  `func_sort` double(6, 2) NOT NULL DEFAULT 0.00 COMMENT '功能排序',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 150 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限系统资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_role
CREATE TABLE `auth_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `owner_id` bigint(20) NOT NULL COMMENT '所属企业主账号id或者超级管理员账号id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名',
  `role_desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `role_type` tinyint(2) NOT NULL DEFAULT 2 COMMENT '角色类型：1.特殊类角色(平台超管，资金方企业主账号，借款方企业主账号)，2.普通类角色',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_role_func
-- ----------------------------

CREATE TABLE `auth_role_func`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auth_role_id` bigint(20) NOT NULL COMMENT '角色Id',
  `auth_func_id` bigint(20) NOT NULL COMMENT '权限Id',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1853 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for china_area
-- ----------------------------
CREATE TABLE `china_area`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地区编码',
  `province` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `city` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市',
  `area` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区、县',
  `parent_id` bigint(20) NOT NULL COMMENT '父级id',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.未删除，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3223 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行政区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company
-- ----------------------------
CREATE TABLE `company`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_type` tinyint(2) DEFAULT NULL COMMENT '企业类型：1.资方，2.借款方',
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业名称',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省id',
  `province_name` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省',
  `city_id` bigint(20) DEFAULT NULL COMMENT '市id',
  `city_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市',
  `area_id` bigint(20) DEFAULT NULL COMMENT '区县id',
  `area_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区域',
  `company_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业地址',
  `company_tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '公司座机号',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人的用户id',
  `create_owner_id` bigint(20) DEFAULT NULL COMMENT '创建人的主账号id',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '负责人用户ID',
  `owner_login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人登录账号',
  `owner_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '负责人姓名',
  `user_mobile_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '法人手机号',
  `legal_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '法人代表',
  `id_card_picture` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '法人身份证照片',
  `id_card_picture_back` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '法人身份证照片背面',
  `register_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '企业注册类型',
  `establishing_time` datetime(0) DEFAULT NULL COMMENT '成立时间',
  `business_scope` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '经营范围',
  `registered_capital` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注册资本',
  `business_license_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '营业执照号码',
  `business_license_picture` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '营业执照照片',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注信息',
  `company_status` tinyint(2) DEFAULT NULL COMMENT '企业资质：0.无效，1.有效',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `sign_account_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '安心签账户id',
  `sign_seal_data` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '电子签章的印章图片base64',
  `id_card_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '法定代表人身份证号',
  `account_mobile_nos` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账单提醒电话号码',
  `sms_consult_phone_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账单详询电话',
  `short_company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业简称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company_contract
-- ----------------------------
CREATE TABLE `company_contract`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_capital_id` bigint(20) NOT NULL COMMENT '资方企业ID',
  `company_borrower_id` bigint(20) NOT NULL COMMENT '借款方企业ID',
  `company_borrower_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '借款方企业名称：主体合同名称',
  `contract_number` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '合同编号',
  `start_time` datetime(0) DEFAULT NULL COMMENT '合同开始时间',
  `end_time` datetime(0) DEFAULT NULL COMMENT '合同结束时间',
  `manage_rate` decimal(10, 8) NOT NULL DEFAULT 0.00000000 COMMENT '管理费率',
  `interest_rate` decimal(10, 8) NOT NULL DEFAULT 0.00000000 COMMENT '利率：年化利率',
  `total_balance` decimal(16, 2) NOT NULL DEFAULT 0.00 COMMENT '总额度',
  `loan_success_balance` decimal(16, 2) DEFAULT NULL COMMENT '企业所有已审核放款成功的总金额：累计',
  `remaining_balance` decimal(16, 2) DEFAULT NULL COMMENT '可用额度=total_balance(总额度) - need_return_balance(已提用融资款额度)',
  `returned_balance` decimal(16, 2) DEFAULT NULL COMMENT '企业已归还金额：累计',
  `need_return_balance` decimal(16, 2) DEFAULT NULL COMMENT '已提用融资款额度 = total_balance(总额度) - remaining_balance(可用额度)',
  `remaining_subitem_balance` decimal(16, 2) DEFAULT NULL COMMENT '可分配的分项额度：总额度-已分配的分享额度总和',
  `had_subitem_balance` decimal(16, 2) DEFAULT NULL COMMENT '已分配的分享额度总和：交易对手的分享额度总和',
  `contract_link` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '合同文件链接地址',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(2) DEFAULT 0 COMMENT '0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）',
  `bank_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank_account_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank_account_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行卡户主名称',
  `project_mg_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `project_second_mg_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `department_manager` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `department_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `branch_bank_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支行名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主体合同：资方与借款方签订的合同' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for counterparty
-- ----------------------------
CREATE TABLE `counterparty`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_capital_id` bigint(20) DEFAULT NULL COMMENT '资方企业ID',
  `company_contract_id` bigint(20) DEFAULT NULL COMMENT '客户合同ID',
  `company_borrower_id` bigint(20) DEFAULT NULL COMMENT '所属借款方企业ID',
  `counterparty_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易对手公司名称',
  `subitem_limit_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '分项限额:可配置',
  `returned_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '回款核销额：计算得出',
  `had_receivable_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '已转应收帐款额：计算得出，累计',
  `total_lending_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '累计放款额：计算得出，累计',
  `subitem_used_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '分项已使用额',
  `subitem_remain_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '分项剩余限额：分项额度的可用余额',
  `rule_ratio` decimal(4, 2) DEFAULT 0.90 COMMENT '规定比例：融资比例',
  `manage_rate` decimal(10, 8) NOT NULL DEFAULT 0.00000000 COMMENT '管理费率',
  `interest_rate` decimal(10, 8) NOT NULL DEFAULT 0.00000000 COMMENT '利率：年化利率',
  `payment_days` int(11) DEFAULT 0 COMMENT '账期：天数',
  `grace_days` int(11) DEFAULT 0 COMMENT '宽限期：天数',
  `grace_rate` decimal(10, 8) NOT NULL DEFAULT 0.00000000 COMMENT '宽限利率',
  `overdue_rate` decimal(10, 8) NOT NULL DEFAULT 0.00000000 COMMENT '利率：年化利率',
  `status` tinyint(2) DEFAULT 0 COMMENT '0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `contract_link` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '合同文件链接地址',
  `returned_transfer_balance` decimal(14, 4) DEFAULT 0.0000 COMMENT '应收账款转让核销金额',
  `factor_contract_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '保理合同编号',
  `factor_limit_check_list_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '保理额度核准明细表编号',
  `factor_service_agreement_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '保理服务协议编号',
  `transfer_balance_contract_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应收账款转让合同编号',
  `base_contract_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '基础合同名称',
  `base_contract_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '基础合同编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交易对手' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for credit_apply
-- ----------------------------
CREATE TABLE `credit_apply`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `status` tinyint(2) NOT NULL COMMENT '状态:状态: -2已作废 -1.运营审核不通过,已作废 1,运营审核中 2.风控审核中 3, \"管理审核中\" 4, 管理层审核 5, 出纳审核 6 已放款 7未还款 8已还款\r\n',
  `credit_apply_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用信申请编号',
  `waybill_count` int(11) DEFAULT 0 COMMENT '运单个数',
  `attachment` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '附件路径（暂定，可能需要修改）',
  `due_date` datetime(0) DEFAULT NULL COMMENT '到期日',
  `repay_type` tinyint(2) DEFAULT NULL COMMENT '还款方式1.先息后本，按月付息 2到期一次行还本付息 3.等额本息 4.等额本金',
  `interest_balance` decimal(12, 2) DEFAULT NULL COMMENT '利息金额',
  `apply_balance` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '本次申请提用金额',
  `can_apply_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '本次可提用金额',
  `counterparty_id` bigint(20) DEFAULT NULL COMMENT '交易对手Id',
  `transfer_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '有效转让金额',
  `has_charge` bit(1) DEFAULT b'0' COMMENT '是否支付服务费',
  `loan_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '放款金额',
  `owner_id` bigint(20) DEFAULT 0 COMMENT '创建人所属主账号',
  `create_user_id` bigint(20) DEFAULT 0 COMMENT '创建人id',
  `fact_ratio` decimal(8, 4) DEFAULT NULL COMMENT '实际比例',
  `flow_node_id` bigint(20) DEFAULT NULL COMMENT '当前所在流程节点id',
  `flow_id` bigint(20) DEFAULT NULL COMMENT '审批流程id',
  `manage_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '管理费用',
  `checked_waybill_count` int(11) DEFAULT NULL COMMENT '已查阅运单条数',
  `grace_date` datetime(0) DEFAULT NULL COMMENT '宽限期日',
  `pdf_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '未签章合同地址',
  `sign_pdf_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子签章后合同地址',
  `loan_time` datetime(0) DEFAULT NULL COMMENT '放款时间',
  `pass_last_flow_node_id` decimal(4, 2) DEFAULT 1.00 COMMENT '流程到达过的最后一个节点.通过+1，拒绝+0.5',
  `review_bill_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审批单地址',
  `after_this_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '本次提用后融资金额',
  `need_check_waybill_count` int(11) DEFAULT NULL COMMENT '需要查阅的运单数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_credit_apply_no`(`credit_apply_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 441 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用信申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for credit_apply_detail
-- ----------------------------
CREATE TABLE `credit_apply_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `transfer_balance` decimal(12, 2) DEFAULT NULL COMMENT '转让金额',
  `remain_transfer_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '剩余转让金额',
  `apply_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '本次提用额',
  `diposit_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '质保金',
  `fact_ratio` decimal(4, 2) NOT NULL DEFAULT 0.00 COMMENT '实际比例',
  `in_rule_ratio` bit(1) DEFAULT NULL COMMENT '是否在比例内',
  `credit_apply_id` bigint(20) DEFAULT NULL COMMENT '用信申请id',
  `subject_claims_order_id` bigint(20) DEFAULT NULL COMMENT '债权用信id',
  `counterparty_id` bigint(20) DEFAULT NULL COMMENT '交易对手id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 511 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用信申请详情:用信申请生成的关联每条标的债权的金额数据。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department
-- ----------------------------
CREATE TABLE `department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) NOT NULL COMMENT '所属企业主账号id或者0(系统)',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `dept_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门层级名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for financial_product
-- ----------------------------
CREATE TABLE `financial_product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `interest_rate` decimal(4, 2) DEFAULT NULL COMMENT '利率',
  `repay_term` int(4) DEFAULT NULL COMMENT '还款周期 月',
  `repay_type` tinyint(2) DEFAULT NULL COMMENT '还款方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '金融产品:暂定' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow
-- ----------------------------
CREATE TABLE `flow`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flow_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '流程名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `company_capital_id` bigint(20) DEFAULT NULL COMMENT '资方公司Id',
  `flow_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '流程业务class',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_node
-- ----------------------------
CREATE TABLE `flow_node`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flow_node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '节点名称',
  `flow_id` bigint(20) DEFAULT NULL COMMENT '流程id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '审批的角色id：type为单人时存储。为多人是存0',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `prev_node_id` bigint(20) DEFAULT NULL COMMENT '前一个节点:若是初始节点为0',
  `next_node_id` bigint(20) DEFAULT NULL COMMENT '后一个节点:若是结尾节点为0',
  `flow_type_id` bigint(20) DEFAULT NULL COMMENT '节点类型Id',
  `limit_balance` decimal(12, 0) DEFAULT NULL COMMENT '限制可跳过金额:默认为0。',
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '对应的状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程节点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_node_type
-- ----------------------------
CREATE TABLE `flow_node_type`  (
  `id` int(11) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '0单人审核 1多人无序 2多人有序',
  `amount` int(4) DEFAULT 0 COMMENT '多人审核人数，单人为0',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '节点类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for program
-- ----------------------------
CREATE TABLE `program`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `third_id` bigint(20) NOT NULL COMMENT '第三方计划id',
  `project_id` bigint(20) NOT NULL COMMENT '项目 id',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者Id',
  `create_owner_id` bigint(20) DEFAULT NULL COMMENT '创建者主账号Id',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者名称',
  `program_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '计划名称',
  `departure_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出发地',
  `arrival_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地',
  `load_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '装货地',
  `arrival_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '卸货工地(工地名称)',
  `arrival_address_warn` tinyint(2) DEFAULT 0 COMMENT '开启卸货地围栏提醒：0未开启,1开启',
  `load_address_warn` tinyint(2) DEFAULT 0 COMMENT '开始装货地围栏提醒：0未开启,1开启',
  `model_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '型号(标号) -- 货物品类',
  `packaged_form` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '包装形式',
  `cargo_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '货值单价',
  `program_no` decimal(10, 2) DEFAULT NULL COMMENT '计划数',
  `units` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '计划单位(如:吨,个,件)',
  `program_status` int(11) DEFAULT NULL COMMENT '计划状态：-1=删除,1=新建，2=启动,3=结束,4=已完成',
  `program_start_date` datetime(0) DEFAULT NULL COMMENT '计划开始日期',
  `program_end_date` datetime(0) DEFAULT NULL COMMENT '计划结束日期',
  `mileage` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '里程数',
  `car_length` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车厢长度',
  `car_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车厢类型',
  `unit_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '运费单价',
  `cargo_desc` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '货物描述',
  `pound_key` int(11) DEFAULT NULL COMMENT '磅差选择类型',
  `pound_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '磅差类型对应的值:\npoundKey = 1(扣磅差比)  poundValue=xx (千分比)\npoundKey = 2(扣磅差值) poundValue=xxx(公斤)\npoundKey = 3(不扣磅差) poundValue=-1(已发货方过磅信息为准)/-2(已收货方过磅信息为准)\npoundKey = 4(不过磅) poundValue=-3/poundKey=5(扣磅差阶梯值)poundValue格式含义见文档',
  `program_type` tinyint(2) DEFAULT 0 COMMENT '计划发货类型：0.手动，1.自动',
  `material_type` tinyint(2) DEFAULT NULL COMMENT '物资类型：0普通物资，1汽车,2.土石方',
  `driver_fill` tinyint(1) DEFAULT 0 COMMENT '司机上传单据时必须完善发货或收货信息 0：否；1：是',
  `cargo_owner_type` tinyint(2) DEFAULT NULL COMMENT '运费担当(1.一票送货;2.两票送货)',
  `income_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '运费收入单价',
  `line_id` bigint(20) DEFAULT 0 COMMENT '关联线路',
  `oil_onit_price` decimal(12, 2) DEFAULT NULL COMMENT '油卡运费单价',
  `contacter` bigint(20) DEFAULT NULL COMMENT '计划联系人',
  `excution_time` datetime(0) DEFAULT NULL COMMENT '预设执行时间',
  `message_switch` tinyint(2) DEFAULT 0 COMMENT '短信配置开关：0未开启,1开启',
  `freight_volume` int(11) DEFAULT NULL COMMENT '派车运量',
  `pound_lesser` int(11) DEFAULT NULL COMMENT '0：以磅差选择方式为准，1：以较小过磅值为准',
  `is_open_location_check` int(11) DEFAULT NULL COMMENT '1:开启校验扫码领单在发货地，0:不开启',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态：0.正常，1.已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  `line_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联线路名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Idx_projectId`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
CREATE TABLE `project`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `third_id` bigint(20) NOT NULL COMMENT '第三方项目id',
  `project_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '项目名称',
  `create_owner_id` bigint(20) DEFAULT NULL COMMENT '创建者的主账号ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者用户ID',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者用户名称',
  `send_user_id` bigint(20) DEFAULT NULL COMMENT '发货方Id',
  `send_company_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '发货方公司名称',
  `transport_user_id` bigint(20) DEFAULT NULL COMMENT '运输方Id',
  `transport_company_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '运输方公司名称',
  `consignee_userId` bigint(20) DEFAULT NULL COMMENT '收货方Id',
  `consignee_company_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '收货方公司名称',
  `payment_user_id` bigint(20) DEFAULT NULL COMMENT '支付方 id',
  `project_status` tinyint(2) DEFAULT 1 COMMENT '项目状态：-1=删除,1=新建，2=启动,3=结束',
  `is_self` tinyint(1) DEFAULT 0 COMMENT '0=对外货主,1=无车承运平台货主',
  `net_weight_only` tinyint(1) DEFAULT 0 COMMENT '是否只完善净重信息 0：否；1：是',
  `material_type` tinyint(2) DEFAULT NULL COMMENT '物资类型：0普通物资，1汽车,2土石方',
  `signing_companyId` bigint(20) DEFAULT NULL COMMENT '签约主体id',
  `signing_company_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '签约主体名称',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态：0.未删除，1,已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '绑定的企业用户id',
  `company_id` bigint(20) DEFAULT NULL COMMENT '监管系统绑定的企业id',
  `counterparty_id` bigint(20) DEFAULT NULL COMMENT '项目所属借款方企业的某个交易对手的ID',
  `counterparty_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目所属借款方企业的某个交易对手的名称',
  `subject_claims_order_count` int(5) DEFAULT 0 COMMENT '应收账款转让订单数',
  `create_owner_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人名称',
  `send_duty_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发货方负责人',
  `transport_duty_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '运输方负责人',
  `consignee_duty_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货方负责人',
  `project_create_time` datetime(0) DEFAULT NULL COMMENT '项目创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_contract
-- ----------------------------
CREATE TABLE `project_contract`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `third_id` bigint(20) NOT NULL COMMENT '第三方合同id',
  `project_id` bigint(20) NOT NULL COMMENT '项目Id',
  `contract` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '合同',
  `consign_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '托运人',
  `transport_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '承运人',
  `transport_company_id` bigint(20) DEFAULT NULL COMMENT '合同乙方对应公司id(即项目中的签约主体id)',
  `good_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '托运货物名称',
  `good_price` double DEFAULT 0 COMMENT '货物价值',
  `tax_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '税号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `phone_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户行名称',
  `bank_nccount_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户行账号',
  `contract_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同编号',
  `calculation_type` int(11) DEFAULT 1 COMMENT '开票方式',
  `priceHandle_type` int(2) DEFAULT NULL COMMENT '运费抹零',
  `execution_start_time` datetime(0) DEFAULT NULL COMMENT '执行周期开始时间',
  `execution_end_time` datetime(0) DEFAULT NULL COMMENT '执行周期结束时间',
  `supplementary_agreement` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '补充协议',
  `tax_rate` decimal(10, 8) NOT NULL DEFAULT 0.00000000 COMMENT '运费差',
  `calc_time_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '结算时间类型 （0=以派车时间计算运费，1=以发货时间计算运费，2=以收货时间计算运费）',
  `preset_record` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '预设信息',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `projectId`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目合同' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repay_bill
-- ----------------------------
CREATE TABLE `repay_bill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `repay_balance` decimal(12, 2) DEFAULT 0.00,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核状态2.待审核 3.已通过 -1.已拒绝',
  `flow_id` bigint(20) DEFAULT NULL COMMENT '审核流程id',
  `flow_node_id` bigint(20) DEFAULT NULL COMMENT '流程节点id',
  `repay_date` datetime(0) DEFAULT NULL COMMENT '还款时间',
  `account_bill_id` bigint(20) DEFAULT NULL COMMENT '账单Id',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `owner_id` bigint(20) DEFAULT 0 COMMENT '创建人所属主账号',
  `create_user_id` bigint(20) DEFAULT 0 COMMENT '创建人id',
  `account_bill_ids` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账单Id:多个',
  `interest_rate_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '利率：年化利率',
  `grace_rate_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '宽限期利率：用于计算宽限期天数的利息',
  `overdue_rate_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '逾期利率：用于计算逾期天数的利息',
  `counterparty_id` bigint(20) DEFAULT NULL COMMENT '交易对手Id',
  `counterparty_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易对手名称',
  `company_borrower_id` bigint(20) DEFAULT NULL COMMENT '借款方企业ID',
  `company_borrower_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '借款方企业名称：主体合同名称',
  `account_bill_balance_info` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '每个账单的还款情况：id,本金，利息，宽限利息，逾期利息；id2,本金，利息，宽限利息，逾期利息',
  `review_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发起的还款订单-发起待出纳确认' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for review_record
-- ----------------------------
CREATE TABLE `review_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `review_result` tinyint(2) DEFAULT NULL COMMENT '审核后的状态',
  `review_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核意见',
  `business_id` bigint(20) DEFAULT NULL COMMENT '审核业务表id:如用信申请id',
  `owner_id` bigint(20) DEFAULT 0 COMMENT '创建人所属主账号',
  `create_user_id` bigint(20) DEFAULT 0 COMMENT '审核人id',
  `pass_status` tinyint(1) DEFAULT NULL COMMENT '是否同意',
  `flow_node_id` bigint(20) DEFAULT NULL COMMENT '流程节点id',
  `flow_id` bigint(20) DEFAULT NULL COMMENT '流程id',
  `flow_node_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '节点名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2276 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审核记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scf_config
-- ----------------------------
CREATE TABLE `scf_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级Id',
  `config_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '配置键',
  `config_value` varchar(1400) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '1' COMMENT '配置值',
  `config_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '常量描述',
  `config_status` tinyint(2) DEFAULT NULL COMMENT '配置是否生效',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 272 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scf_user
-- ----------------------------
CREATE TABLE `scf_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) DEFAULT 0 COMMENT '主账号id',
  `user_type` tinyint(4) DEFAULT 0 COMMENT '用户类型 1.  系统超级管理员，2.资金方用户，3.借款方用户',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色Id',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `user_password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '姓名',
  `company_id` bigint(20) DEFAULT NULL COMMENT '企业id',
  `company_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司名称',
  `user_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户邮箱',
  `user_mobile_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '联系电话',
  `user_avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户头像',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态：0.正常，1,已删除',
  `last_login_time` datetime(0) DEFAULT NULL COMMENT '上次登录时间',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 227 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scf_user_config
-- ----------------------------
CREATE TABLE `scf_user_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) DEFAULT NULL COMMENT '所属用户id',
  `config_id` bigint(20) DEFAULT NULL COMMENT '配置表id',
  `config_value` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置值',
  `config_status` tinyint(2) DEFAULT NULL COMMENT '是否生效：0.失效，1.生效',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.正常，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户配置关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_claims_order
-- ----------------------------
CREATE TABLE `subject_claims_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_claims_order_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标的债权订单号',
  `counterparty_id` bigint(20) DEFAULT NULL COMMENT '交易对手id',
  `counterparty_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易对手名称',
  `transfer_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '转让金额',
  `can_apply_balance` decimal(12, 2) DEFAULT 0.00 COMMENT '本次可提额度',
  `waybill_count` int(5) DEFAULT NULL COMMENT '运单个数',
  `financial_product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '金融产品名称',
  `deleted` bit(1) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `financial_product_id` bigint(20) DEFAULT NULL COMMENT '金融产品Id',
  `owner_id` bigint(20) DEFAULT 0 COMMENT '创建人所属主账号',
  `create_user_id` bigint(20) DEFAULT 0 COMMENT '创建人id',
  `review_status` tinyint(2) DEFAULT NULL COMMENT '审核状态：-1.运营审核不通过，0.初始化，1.运营审核中',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目Id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_idx_claims_order_no`(`subject_claims_order_no`) USING BTREE,
  INDEX `idx_counterparty_name`(`counterparty_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2481 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标的债权订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for waybill
-- ----------------------------
CREATE TABLE `waybill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `third_id` bigint(20) NOT NULL COMMENT '第三方运单id',
  `waybill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '运单唯一编号',
  `departure_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出发地',
  `arrival_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地',
  `cargo_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '货物类型',
  `cargo_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '货物数量',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目Id',
  `project_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `program_id` bigint(20) DEFAULT NULL COMMENT '计划Id',
  `program_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '计划名称',
  `driver_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '驾驶员姓名',
  `driver_mobile_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '驾驶员手机号',
  `license_plate_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车牌号',
  `car_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车厢类型',
  `axle_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车轴数量',
  `car_length` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车厢长度',
  `cargo_tonnage` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '核载吨位',
  `fuel_type` tinyint(2) DEFAULT 0 COMMENT '燃料类型: 1=燃油 2=LNG 3=新能源',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注说明',
  `unit` tinyint(2) DEFAULT NULL COMMENT '货物单位',
  `upload_send_invoice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货单据图片',
  `upload_receive_invoice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货单据图片',
  `gross_weight_by_send` decimal(12, 3) DEFAULT 0.000 COMMENT '发货方填写的发货毛重',
  `tare_weight_by_send` decimal(12, 3) DEFAULT 0.000 COMMENT '发货方填写的发货皮重',
  `net_weight_by_send` decimal(12, 3) DEFAULT 0.000 COMMENT '发货方填写的发货净重',
  `send_gross_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的发货毛重',
  `send_tare_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的发货皮重',
  `send_net_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的发货净重',
  `receive_gross_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的收货毛重',
  `receive_tare_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的收货皮重',
  `receive_net_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的收货净重',
  `allow_difference` tinyint(4) DEFAULT 0 COMMENT '允许磅差类型',
  `allow_difference_val` int(11) DEFAULT 0 COMMENT '允许的磅差比例值',
  `allow_difference_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '0.0' COMMENT '允许的磅差数',
  `unit_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '运费单价',
  `value_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '货值单价',
  `total_price` decimal(13, 2) DEFAULT NULL COMMENT '总运费额（成本）',
  `income_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '收入单价',
  `etc_card_amount` decimal(10, 2) DEFAULT NULL COMMENT 'ETC支付金额',
  `cash_pay_amount` decimal(13, 2) DEFAULT NULL COMMENT '现金支付金额',
  `deposit_amount` decimal(10, 2) DEFAULT NULL COMMENT '押金金额',
  `oil_card_amount` decimal(10, 2) DEFAULT NULL COMMENT '油卡支付金额',
  `gas_card_amount` decimal(10, 2) DEFAULT 0.00 COMMENT '气卡支付金额',
  `oil_used_credit` decimal(10, 2) DEFAULT 0.00 COMMENT '运单已使用授信',
  `oil_credit_limit` decimal(10, 2) DEFAULT 0.00 COMMENT '授信额度',
  `operator_type` tinyint(2) DEFAULT 0 COMMENT '操作类型：0=双方完善，1=发货方为准，2=收货方为准',
  `loading_time` datetime(0) DEFAULT NULL COMMENT '装货时间',
  `receive_time` datetime(0) DEFAULT NULL COMMENT '收货时间',
  `bill_pass_time` datetime(0) DEFAULT NULL COMMENT '单据通过时间',
  `pay_freight_time` datetime(0) DEFAULT NULL COMMENT '承运方支付运费时间',
  `edit_send_time` datetime(0) DEFAULT NULL COMMENT '完善发货单时间',
  `edit_receive_time` datetime(0) DEFAULT NULL COMMENT '完善收货单时间',
  `review_send_time` datetime(0) DEFAULT NULL COMMENT '复核发货单时间',
  `review_receive_time` datetime(0) DEFAULT NULL COMMENT '复核收货单时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态：0.未删除,1.已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  `subject_claims_order_id` bigint(20) DEFAULT NULL COMMENT '标的债权订单id',
  `pdf_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '运输合同地址',
  `cfca_pdf_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cfca存证文件地址',
  `waybill_status` tinyint(1) DEFAULT NULL COMMENT '运单状态：-2,运单无效；-1.异常 ,0.正常,',
  `gps_count` bigint(20) DEFAULT NULL COMMENT '轨迹点个数',
  `invoice_money` decimal(16, 2) DEFAULT NULL COMMENT '结算金额',
  `waybill_create_time` datetime(0) DEFAULT NULL COMMENT '运单创建时间',
  `cfca_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cfca存证的编号',
  `gps_type` tinyint(2) DEFAULT NULL COMMENT '轨迹点类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Idx_projectId_programId`(`project_id`, `program_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18652 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for waybill_check_record
-- ----------------------------
CREATE TABLE `waybill_check_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `waybill_id` bigint(20) DEFAULT NULL COMMENT '运单id',
  `check_user_id` bigint(20) DEFAULT NULL COMMENT '查阅人id',
  `check_owner_id` bigint(20) DEFAULT NULL COMMENT '查阅人主账号id',
  `credit_apply_id` bigint(20) DEFAULT NULL COMMENT '用信申请id',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.未删除，1,已删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运单查阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for waybill_operation_history
-- ----------------------------
CREATE TABLE `waybill_operation_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `waybill_id` bigint(20) DEFAULT NULL COMMENT '运单id',
  `handle_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作内容',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除状态：0.未删除，1,已删除',
  `handle_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74874 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运单操作历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for waybill_validate
-- ----------------------------
CREATE TABLE `waybill_validate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `third_id` bigint(20) NOT NULL COMMENT '第三方运单id',
  `waybill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '运单唯一编号',
  `departure_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '出发地',
  `arrival_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '目的地',
  `driver_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '驾驶员姓名',
  `driver_mobile_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '驾驶员手机号',
  `license_plate_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '车牌号',
  `upload_send_invoice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货单据图片',
  `upload_receive_invoice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货单据图片',
  `gross_weight_by_send` decimal(12, 3) DEFAULT 0.000 COMMENT '发货方填写的发货毛重',
  `tare_weight_by_send` decimal(12, 3) DEFAULT 0.000 COMMENT '发货方填写的发货皮重',
  `net_weight_by_send` decimal(12, 3) DEFAULT 0.000 COMMENT '发货方填写的发货净重',
  `send_gross_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的发货毛重',
  `send_tare_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的发货皮重',
  `send_net_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的发货净重',
  `receive_gross_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的收货毛重',
  `receive_tare_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的收货皮重',
  `receive_net_weight` decimal(12, 3) DEFAULT 0.000 COMMENT '收货方填写的收货净重',
  `allow_difference` tinyint(4) DEFAULT 0 COMMENT '允许磅差类型',
  `allow_difference_val` int(11) DEFAULT 0 COMMENT '允许的磅差比例值',
  `allow_difference_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '0.0' COMMENT '允许的磅差数',
  `unit_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '运费单价',
  `value_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '货值单价',
  `total_price` decimal(13, 2) DEFAULT NULL COMMENT '总运费额（成本）',
  `income_price` decimal(12, 4) DEFAULT 0.0000 COMMENT '收入单价：结算单价',
  `etc_card_amount` decimal(10, 2) DEFAULT NULL COMMENT 'ETC支付金额',
  `cash_pay_amount` decimal(13, 2) DEFAULT NULL COMMENT '现金支付金额',
  `deposit_amount` decimal(10, 2) DEFAULT NULL COMMENT '押金金额',
  `oil_card_amount` decimal(10, 2) DEFAULT NULL COMMENT '油卡支付金额',
  `gas_card_amount` decimal(10, 2) DEFAULT 0.00 COMMENT '气卡支付金额',
  `oil_used_credit` decimal(10, 2) DEFAULT 0.00 COMMENT '运单已使用授信',
  `oil_credit_limit` decimal(10, 2) DEFAULT 0.00 COMMENT '授信额度',
  `operator_type` tinyint(2) DEFAULT 0 COMMENT '操作类型：0=双方完善，1=发货方为准，2=收货方为准',
  `loading_time` datetime(0) DEFAULT NULL COMMENT '装货时间',
  `receive_time` datetime(0) DEFAULT NULL COMMENT '收货时间',
  `bill_pass_time` datetime(0) DEFAULT NULL COMMENT '单据通过时间',
  `pay_freight_time` datetime(0) DEFAULT NULL COMMENT '承运方支付运费时间',
  `edit_send_time` datetime(0) DEFAULT NULL COMMENT '完善发货单时间',
  `edit_receive_time` datetime(0) DEFAULT NULL COMMENT '完善收货单时间',
  `review_send_time` datetime(0) DEFAULT NULL COMMENT '复核发货单时间',
  `review_receive_time` datetime(0) DEFAULT NULL COMMENT '复核收货单时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态：0.未删除,1.已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  `subject_claims_order_id` bigint(20) DEFAULT NULL COMMENT '标的债权订单id',
  `pdf_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '运输合同地址',
  `cfca_pdf_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cfca存证文件地址',
  `waybill_status` tinyint(1) DEFAULT NULL COMMENT '运单状态：-2,运单无效；-1.异常 ,0.正常,',
  `id_card_no_send` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货方：身份证号码',
  `id_card_picture_send` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货方：身份证照片',
  `id_card_picture_back_send` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货方：身份证照片背面',
  `business_license_no_send` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货方：营业执照号码',
  `legal_person_send` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货方：法人代表',
  `business_license_picture_send` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货方：营业执照照片',
  `vehicle_operating_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '道路运输许可证号码',
  `licenceo_picture` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '从业资格证图片',
  `load_location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '装货地坐标',
  `arrival_location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '卸货地坐标',
  `id_card_no_consignee` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货方：身份证号码',
  `id_card_picture_consignee` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货方：身份证照片',
  `id_card_picture_back_consignee` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货方：身份证照片背面',
  `business_license_no_consignee` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货方：营业执照号码',
  `legal_person_consignee` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货方：法人代表',
  `business_license_picture_consignee` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货方：营业执照照片',
  `waybill_validate` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gps_count` bigint(20) DEFAULT NULL COMMENT '轨迹点个数',
  `invoice_money` decimal(16, 2) DEFAULT NULL COMMENT '结算金额',
  `waybill_create_time` datetime(0) DEFAULT NULL COMMENT '运单创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20298 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运单表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- 金融平台sql整理


-- 菜单权限表（auth_function）：
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (1, 40, NULL, '用信规则配置', NULL, 'creditRegulationConfigMgmt.html', 1, 1.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (2, 40, NULL, '企业管理', NULL, 'companyMgmt.html', 1, 2.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (3, 0, NULL, '基础信息管理', NULL, NULL, 1, 11.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (4, 3, NULL, '组织架构', NULL, 'departmentMgmt.html', 1, 4.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (5, 3, NULL, '角色管理', NULL, 'authRoleMgmt.html', 1, 5.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (6, 0, NULL, '金融产品管理', NULL, NULL, 1, 6.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (7, 0, NULL, '保全信息管理', NULL, NULL, 1, 7.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (8, 0, NULL, '客户管理', NULL, NULL, 1, 2.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (9, 8, NULL, '客户信息维护', NULL, 'companyContractMgmt.html', 1, 9.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (10, 8, NULL, '新增客户', NULL, 'companyContract/companyContractDetail.html', 1, 9.10, 0, '2020-03-09 18:40:54', '2020-03-09 18:40:56');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (11, 0, NULL, '运营审核', NULL, NULL, 1, 3.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (12, 11, NULL, '放款申请审核', NULL, 'creditApply.html?type=4', 1, 11.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (13, 11, NULL, '审核记录', NULL, 'creditApply.html?type=4&readOnly=true', 1, 12.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (14, 0, NULL, '风控审核', NULL, NULL, 1, 4.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (15, 14, NULL, '放款申请审核', NULL, 'creditApply.html?type=5', 1, 14.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (16, 14, NULL, '审核记录', NULL, 'creditApply.html?type=5&readOnly=true', 1, 15.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (17, 0, NULL, '管理层审核', NULL, NULL, 1, 6.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (18, 17, NULL, '放款申请审核', NULL, 'creditApply.html?type=6', 1, 17.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (19, 17, NULL, '审核记录', NULL, 'creditApply.html?type=6&readOnly=true', 1, 18.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (20, 0, NULL, '财务审核', NULL, NULL, 1, 5.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (21, 20, NULL, '放款申请审核', NULL, 'creditApply.html?type=7', 1, 20.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (22, 20, NULL, '审核记录', NULL, 'creditApply.html?type=7&readOnly=true', 1, 21.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (23, 61, NULL, '放款管理', NULL, 'creditApply.html?type=9', 1, 22.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (24, 0, NULL, '收款管理', NULL, 'repayConfigList.html', 1, 9.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (25, 0, NULL, '账单管理', '资金方账单管理', 'accountBill.html', 1, 8.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (26, 0, NULL, '统计报表', NULL, '', 1, 10.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (27, 0, NULL, '我的额度', NULL, 'projectMgmt.html', 1, 0.50, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (28, 0, NULL, '用款申请', NULL, 'subjectClaimsOrder.html?reviewStatus=0', 1, 1.40, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (29, 0, NULL, '用款申请记录', NULL, NULL, 1, 1.50, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (30, 29, NULL, '审核中', NULL, 'creditApply.html?type=1', 1, 32.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (31, 29, NULL, '已放款', NULL, 'creditApply.html?type=2', 1, 33.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (32, 29, NULL, '已结清', NULL, 'creditApply.html?type=3', 1, 34.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (33, 0, NULL, '系统配置管理', NULL, 'scfConfigMgmt.html', 1, 999.00, 0, '2020-03-12 17:40:03', '2020-03-12 17:40:05');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (38, 142, NULL, '还款记录', NULL, 'repayConfigList.html?readOnly=true', 1, 32.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (39, 40, NULL, '放款审核配置', NULL, 'loanReviewConfig.html', 1, 26.00, 0, '2020-03-24 10:23:51', '2020-03-24 10:23:55');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (40, 0, NULL, '系统管理', NULL, NULL, 1, 12.00, 0, '2020-03-25 16:37:21', '2020-03-25 16:37:23');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (41, 4, 'ORG_LIST', '组织架构查看', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (42, 4, 'ORG_DEPT_MGMT', '组织架构部门管理', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (43, 4, 'ORG_USER_ADD', '组织架构用户注册', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (44, 4, 'ORG_USER_EDIT', '组织架构用户编辑', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (45, 4, 'ORG_USER_DELETE', '组织架构用户删除', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (46, 5, 'ROLE_LIST', '角色管理列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (47, 5, 'ROLE_ADD', '角色管理新增', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (48, 5, 'ROLE_EDIT', '角色管理编辑', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (49, 5, 'ROLE_DELETE', '角色管理删除', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (50, 2, 'COMPANY_LIST', '企业管理列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (51, 2, 'COMPANY_DETAIL', '企业管理详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (52, 2, 'COMPANY_ADD', '注册企业', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (53, 1, 'CREDIT_REGULATION_LIST', '用信规则查看', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (54, 1, 'CREDIT_REGULATION_EDIT', '用信规则编辑', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (55, 39, 'LOAN_REVIEW_CONFIG_LIST', '放款审核配置查看', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (56, 39, 'LOAN_REVIEW_CONFIG_EDIT', '放款审核配置编辑', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (57, 9, 'COMPANY_CONTRACT_LIST', '客户信息列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (58, 9, 'COUNTERPARTY_LIST', '查看客户交易对手', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (59, 9, 'COUNTERPARTY_ADD', '新增交易对手', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (60, 10, 'COMPANY_CONTRACT_ADD', '新增客户', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (61, 0, NULL, '出纳审核', NULL, NULL, 1, 7.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (62, 61, NULL, '放款申请审核', NULL, 'creditApply.html?type=8', 1, 20.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (63, 61, NULL, '审核记录', NULL, 'creditApply.html?type=8&readOnly=true', 1, 21.00, 0, '2020-03-07 15:55:37', '2020-03-07 15:55:40');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (64, 12, 'OPERATION_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (65, 12, 'OPERATION_LOAN_REVIEW_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (66, 12, 'OPERATION_LOAN_REVIEW', '放款审核详情审核操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (67, 12, 'OPERATION_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (68, 12, 'OPERATION_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (69, 12, 'OPERATION_LOAN_WAYBILL_MARK', '放款申请已转让运单标记', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (70, 13, 'OPERATION_RECORD_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (71, 13, 'OPERATION_RECORD_LOAN_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (72, 13, 'OPERATION_RECORD_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (73, 13, 'OPERATION_RECORD_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (74, 15, 'RISK_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (75, 15, 'RISK_LOAN_REVIEW_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (76, 15, 'RISK_LOAN_REVIEW', '放款审核详情审核操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (77, 15, 'RISK_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (78, 15, 'RISK_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (79, 16, 'RISK_RECORD_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (80, 16, 'RISK_RECORD_LOAN_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (81, 16, 'RISK_RECORD_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (82, 16, 'RISK_RECORD_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (83, 18, 'MGMT_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (84, 18, 'MGMT_LOAN_REVIEW_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (85, 18, 'MGMT_LOAN_REVIEW', '放款审核详情审核操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (86, 18, 'MGMT_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (87, 18, 'MGMT_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (88, 19, 'MGMT_RECORD_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (89, 19, 'MGMT_RECORD_LOAN_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (90, 19, 'MGMT_RECORD_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (91, 19, 'MGMT_RECORD_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (92, 21, 'FINANCE_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (93, 21, 'FINANCE_LOAN_REVIEW_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (94, 21, 'FINANCE_LOAN_REVIEW', '放款审核详情审核操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (95, 21, 'FINANCE_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (96, 21, 'FINANCE_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (97, 22, 'FINANCE_RECORD_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (98, 22, 'FINANCE_RECORD_LOAN_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (99, 22, 'FINANCE_RECORD_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (100, 22, 'FINANCE_RECORD_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (101, 62, 'CASHIER_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (102, 62, 'CASHIER_LOAN_REVIEW_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (103, 62, 'CASHIER_LOAN_REVIEW', '放款审核详情审核操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (104, 62, 'CASHIER_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (105, 62, 'CASHIER_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (106, 63, 'CASHIER_RECORD_LOAN_LIST', '放款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (107, 63, 'CASHIER_RECORD_LOAN_DETAIL', '查看放款申请审核详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (108, 63, 'CASHIER_RECORD_LOAN_FINANCING_DETAIL', '查看放款申请融资详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (109, 63, 'CASHIER_RECORD_LOAN_WAYBILL_LIST', '查看放款申请已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (110, 23, 'CASHIER_LOAN_MGMT_LIST', '放款管理列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (111, 25, 'ACCOUNT_BILL_LIST', '账单管理列表', '资金方账单管理列表', NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (112, 25, 'ACCOUNT_BILL_MGMT', '账单管理操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (113, 24, 'REPAY_LIST', '收款管理列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (114, 24, 'REPAY_MGMT', '收款管理确认收款', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (115, 27, 'PROJECT_LIST', '我的额度列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (116, 27, 'PROJECT_SUBJECT_CLAIMS_LIST', '查看已转让应收账款订单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (117, 27, 'PROJECT_PROGRAM_LIST', '查看项目与计划', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (118, 27, 'PROJECT_WAYBILL_LIST', '查看未转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (119, 27, 'SUBJECT_CLAIMS_CREDIT_APPLY', '发起用款申请', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (120, 27, 'SUBJECT_CLAIMS_WAYBILL_LIST', '查看已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (121, 27, 'SUBJECT_CLAIMS_WAYBILL_MGMT', '已转让运单操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (122, 28, 'CREDIT_APPLY', '发起用款申请', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (123, 28, 'CREDIT_APPLY_WAYBILL_LIST', '查看已转让运单', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (124, 28, 'CREDIT_APPLY_WAYBILL_MGMT', '已转让运单操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (125, 30, 'BORROWER_REVIEWING_CREDIT_APPLY_LIST', '用款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (126, 30, 'BORROWER_REVIEWING_CREDIT_APPLY_DETAIL', '用款申请详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (127, 30, 'BORROWER_REVIEWING_CREDIT_REAPPLY', '重新申请用款', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (128, 31, 'BORROWER_LOANED_CREDIT_APPLY_LIST', '用款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (129, 31, 'BORROWER_LOANED_CREDIT_APPLY_DETAIL', '用款申请详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (131, 32, 'BORROWER_REPAYED_CREDIT_APPLY_LIST', '用款申请列表', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (132, 32, 'BORROWER_REPAYED_CREDIT_APPLY_DETAIL', '用款申请详情', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (134, 9, 'COUNTERPARTY_REVIEW', '交易对手审核', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (135, 9, 'COMPANY_CONTRACT_REVIEW', '客户信息审核', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (136, 143, 'ACCOUNT_BILL_REPAY', '账单管理还款操作', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (138, 0, NULL, '首页', NULL, 'home.html', 1, 0.00, 0, '2020-03-31 15:36:46', '2020-03-31 15:36:48');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (141, 143, 'ACCOUNT_BILL_DETAIL', '账单管理详情', NULL, NULL, 2, 2.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (142, 0, NULL, '账单还款', NULL, NULL, 1, 2.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (143, 142, '142', '账单管理', '借款方账单管理', 'accountBill.html', 1, 8.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (144, 143, 'ACCOUNT_BILL_LIST', '账单管理列表', '借款方账单管理列表', NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (145, 38, 'REPAY_RECORD_LIST', '还款记录列表', '借款方还款记录列表', NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (146, 40, NULL, '账单短信设置', NULL, 'accounNotifyMobileNos.html', 1, 30.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (147, 146, 'ACCOUN_NOTIFY_MOBILENOS_MGMT', '账单短信设置', NULL, NULL, 1, 31.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (148, 27, 'SUBJECT_CLAIMS_WAYBILL_ADD', '生成已转让应收账款', NULL, NULL, 2, 1.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');
INSERT INTO `auth_function`(`id`, `parent_id`, `func_key`, `func_name`, `func_desc`, `func_url`, `func_type`, `func_sort`, `deleted`, `create_time`, `update_time`) VALUES (149, 0, NULL, '系统配置', NULL, 'scfConfigMgmt.html', 1, 32.00, 0, '2020-03-25 16:50:32', '2020-03-25 16:50:33');



-- 角色表（auth_role）:


INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (1, 1, '平台超级管理员', '平台超级管理员', 1, 0, '2020-03-09 18:05:43', '2020-03-12 17:42:14');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (2, 1, '企业主账号', '企业主账号', 2, 0, '2020-02-21 15:25:30', '2020-03-27 14:32:27');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (3, 1, '借款方主账号', '借款方主账号', 3, 0, '2020-03-09 18:06:36', '2020-03-27 16:52:50');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (4, 1, '运管人员', '运管人员', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:34:04');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (5, 1, '风险经理', '风险一', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:35:10');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (6, 1, '风险部长', '风险二', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:35:40');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (7, 1, '财务经理', '财务经理', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:36:29');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (8, 1, '财务部长', '财务部长', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:36:35');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (9, 1, '出纳', '出纳', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:37:57');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (10, 1, '副总经理', '副总经理', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:38:14');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (11, 1, '总经理', '总经理', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:38:22');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (12, 1, '执行董事', '执行董事', 4, 0, '2020-03-23 10:56:20', '2020-03-27 14:38:28');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (13, 1, '客户经理', '客户经理', 4, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (14, 1, '企业管理员', '借款方企业管理员', 5, 0, '2020-03-27 16:32:52', '2020-03-27 16:54:47');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (15, 1, '运营角色', '借款方运营角色', 5, 0, '2020-03-27 16:34:01', '2020-03-27 16:55:53');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (16, 2, '企业主账号', '企业主账号', 2, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (17, 2, '借款方主账号', '借款方主账号', 3, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (18, 2, '运管人员', '运管人员', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (19, 2, '风险经理', '风险一', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (20, 2, '风险部长', '风险二', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (21, 2, '财务经理', '财务经理', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (22, 2, '财务部长', '财务部长', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (23, 2, '出纳', '出纳', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (24, 2, '副总经理', '副总经理', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (25, 2, '总经理', '总经理', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (26, 2, '执行董事', '执行董事', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (27, 2, '客户经理', '客户经理', 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (28, 2, '企业管理员', '借款方企业管理员', 5, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (29, 2, '运营角色', '借款方运营角色', 5, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (41, 1, '借款方全部权限角色', '拥有借款方所有权限，但是该角色被隐藏，角色列表上看不到', 7, 0, '2020-03-09 18:05:43', '2020-03-12 17:42:14');
INSERT INTO `auth_role`(`id`, `owner_id`, `role_name`, `role_desc`, `role_type`, `deleted`, `create_time`, `update_time`) VALUES (42, 2, '借款方全部权限角色', '拥有借款方所有权限，但是该角色被隐藏，角色列表上看不到', 7, 0, '2020-03-09 18:05:43', '2020-03-12 17:42:14');




-- 角色权限关联表（auth_role_func）：

	INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (21, 2, 3, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (22, 2, 4, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (23, 2, 41, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (24, 2, 42, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (25, 2, 43, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (26, 2, 44, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (27, 2, 45, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (28, 2, 5, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (29, 2, 46, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (30, 2, 47, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (31, 2, 48, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (32, 2, 49, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (33, 2, 8, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (34, 2, 9, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (35, 2, 57, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (36, 2, 58, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (37, 2, 11, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (38, 2, 12, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (39, 2, 64, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (40, 2, 65, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (41, 2, 67, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (42, 2, 68, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (49, 2, 40, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (50, 2, 1, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (51, 2, 53, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (52, 2, 54, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (53, 2, 2, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (54, 2, 50, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (55, 2, 51, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (56, 2, 52, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (57, 2, 39, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (58, 2, 55, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (59, 2, 56, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (60, 2, 14, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (61, 2, 15, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (62, 2, 74, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (63, 2, 75, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (64, 2, 77, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (65, 2, 78, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (71, 2, 17, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (72, 2, 18, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (73, 2, 83, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (74, 2, 84, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (75, 2, 86, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (76, 2, 87, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (82, 2, 20, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (83, 2, 21, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (84, 2, 92, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (85, 2, 93, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (86, 2, 95, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (87, 2, 96, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (93, 2, 61, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (94, 2, 62, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (95, 2, 101, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (96, 2, 102, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (97, 2, 104, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (98, 2, 105, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (106, 2, 25, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (107, 2, 111, 0, '2020-03-27 14:32:27', '2020-03-27 14:32:27');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (108, 4, 8, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (109, 4, 9, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (110, 4, 57, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (111, 4, 58, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (112, 4, 59, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (113, 4, 10, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (114, 4, 60, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (115, 4, 11, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (116, 4, 12, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (117, 4, 64, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (118, 4, 65, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (119, 4, 66, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (120, 4, 67, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (121, 4, 68, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (122, 4, 69, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (123, 4, 13, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (124, 4, 70, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (125, 4, 71, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (126, 4, 72, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (127, 4, 73, 0, '2020-03-27 14:34:04', '2020-03-27 14:34:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (128, 5, 8, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (129, 5, 9, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (130, 5, 57, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (131, 5, 58, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (132, 5, 134, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (133, 5, 14, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (134, 5, 15, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (135, 5, 74, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (136, 5, 75, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (137, 5, 76, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (138, 5, 77, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (139, 5, 78, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (140, 5, 16, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (141, 5, 79, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (142, 5, 80, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (143, 5, 81, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (144, 5, 82, 0, '2020-03-27 14:35:10', '2020-03-27 14:35:10');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (145, 6, 14, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (146, 6, 15, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (147, 6, 74, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (148, 6, 75, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (149, 6, 76, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (150, 6, 77, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (151, 6, 78, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (152, 6, 16, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (153, 6, 79, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (154, 6, 80, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (155, 6, 81, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (156, 6, 82, 0, '2020-03-27 14:35:40', '2020-03-27 14:35:40');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (157, 7, 20, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (158, 7, 21, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (159, 7, 92, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (160, 7, 93, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (161, 7, 94, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (162, 7, 95, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (163, 7, 96, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (164, 7, 22, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (165, 7, 97, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (166, 7, 98, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (167, 7, 99, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (168, 7, 100, 0, '2020-03-27 14:35:51', '2020-03-27 14:35:51');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (169, 8, 20, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (170, 8, 21, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (171, 8, 92, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (172, 8, 93, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (173, 8, 94, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (174, 8, 95, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (175, 8, 96, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (176, 8, 22, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (177, 8, 97, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (178, 8, 98, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (179, 8, 99, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (180, 8, 100, 0, '2020-03-27 14:35:59', '2020-03-27 14:35:59');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (181, 7, 25, 0, '2020-03-27 14:36:29', '2020-03-27 14:36:29');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (182, 7, 111, 0, '2020-03-27 14:36:29', '2020-03-27 14:36:29');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (183, 7, 112, 0, '2020-03-27 14:36:29', '2020-03-27 14:36:29');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (184, 8, 25, 0, '2020-03-27 14:36:35', '2020-03-27 14:36:35');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (185, 8, 111, 0, '2020-03-27 14:36:35', '2020-03-27 14:36:35');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (186, 8, 112, 0, '2020-03-27 14:36:35', '2020-03-27 14:36:35');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (187, 9, 61, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (188, 9, 62, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (189, 9, 101, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (190, 9, 102, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (191, 9, 103, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (192, 9, 104, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (193, 9, 105, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (194, 9, 63, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (195, 9, 106, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (196, 9, 107, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (197, 9, 108, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (198, 9, 109, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (199, 9, 23, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (200, 9, 110, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (201, 9, 24, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (202, 9, 113, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (203, 9, 114, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (204, 9, 25, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (205, 9, 111, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (206, 9, 112, 0, '2020-03-27 14:37:57', '2020-03-27 14:37:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (207, 10, 17, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (208, 10, 18, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (209, 10, 83, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (210, 10, 84, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (211, 10, 85, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (212, 10, 86, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (213, 10, 87, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (214, 10, 19, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (215, 10, 88, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (216, 10, 89, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (217, 10, 90, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (218, 10, 91, 0, '2020-03-27 14:38:14', '2020-03-27 14:38:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (219, 11, 17, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (220, 11, 18, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (221, 11, 83, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (222, 11, 84, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (223, 11, 85, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (224, 11, 86, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (225, 11, 87, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (226, 11, 19, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (227, 11, 88, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (228, 11, 89, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (229, 11, 90, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (230, 11, 91, 0, '2020-03-27 14:38:22', '2020-03-27 14:38:22');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (231, 12, 17, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (232, 12, 18, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (233, 12, 83, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (234, 12, 84, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (235, 12, 85, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (236, 12, 86, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (237, 12, 87, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (238, 12, 19, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (239, 12, 88, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (240, 12, 89, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (241, 12, 90, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (242, 12, 91, 0, '2020-03-27 14:38:28', '2020-03-27 14:38:28');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (243, 13, 11, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (244, 13, 12, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (245, 13, 64, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (246, 13, 65, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (247, 13, 67, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (248, 13, 68, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (255, 13, 14, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (256, 13, 15, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (257, 13, 74, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (258, 13, 75, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (259, 13, 77, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (260, 13, 78, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (266, 13, 17, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (267, 13, 18, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (268, 13, 83, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (269, 13, 84, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (270, 13, 86, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (271, 13, 87, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (277, 13, 20, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (278, 13, 21, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (279, 13, 92, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (280, 13, 93, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (281, 13, 95, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (282, 13, 96, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (288, 13, 61, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (289, 13, 62, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (290, 13, 101, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (291, 13, 102, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (292, 13, 104, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (293, 13, 105, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (299, 13, 25, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (300, 13, 111, 0, '2020-03-27 14:59:03', '2020-03-27 14:59:03');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (301, 5, 135, 0, '2020-03-27 15:54:14', '2020-03-27 15:54:17');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (302, 3, 3, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (303, 3, 4, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (304, 3, 41, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (305, 3, 42, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (306, 3, 43, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (307, 3, 44, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (308, 3, 45, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (309, 3, 5, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (310, 3, 46, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (311, 3, 47, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (312, 3, 48, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (313, 3, 49, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (314, 3, 143, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (315, 3, 144, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (316, 3, 112, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (317, 3, 27, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (318, 3, 115, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (319, 3, 116, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (320, 3, 117, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (321, 3, 118, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (322, 3, 29, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (323, 3, 30, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (324, 3, 125, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (325, 3, 126, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (326, 3, 31, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (327, 3, 128, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (328, 3, 129, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (330, 3, 32, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (331, 3, 131, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (332, 3, 132, 0, '2020-03-27 16:52:50', '2020-03-27 16:52:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (334, 14, 3, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (335, 14, 4, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (336, 14, 41, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (337, 14, 42, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (338, 14, 43, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (339, 14, 44, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (340, 14, 45, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (341, 14, 5, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (342, 14, 46, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (343, 14, 47, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (344, 14, 48, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (345, 14, 49, 0, '2020-03-27 16:54:47', '2020-03-27 16:54:47');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (346, 15, 143, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (347, 15, 144, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (349, 15, 136, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (350, 15, 27, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (351, 15, 115, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (352, 15, 116, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (353, 15, 117, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (354, 15, 118, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (355, 15, 119, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (356, 15, 120, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (357, 15, 121, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (358, 15, 28, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (359, 15, 122, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (360, 15, 123, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (361, 15, 124, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (362, 15, 29, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (363, 15, 30, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (364, 15, 125, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (365, 15, 126, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (366, 15, 127, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (367, 15, 31, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (368, 15, 128, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (369, 15, 129, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (371, 15, 32, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (372, 15, 131, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (373, 15, 132, 0, '2020-03-27 16:55:53', '2020-03-27 16:55:53');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (375, 16, 3, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (376, 16, 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (377, 16, 41, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (378, 16, 42, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (379, 16, 43, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (380, 16, 44, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (381, 16, 45, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (382, 16, 5, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (383, 16, 46, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (384, 16, 47, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (385, 16, 48, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (386, 16, 49, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (387, 16, 8, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (388, 16, 9, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (389, 16, 57, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (390, 16, 58, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (391, 16, 11, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (392, 16, 12, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (393, 16, 64, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (394, 16, 65, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (395, 16, 67, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (396, 16, 68, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (403, 16, 40, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (404, 16, 1, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (405, 16, 53, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (406, 16, 54, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (407, 16, 2, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (408, 16, 50, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (409, 16, 51, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (410, 16, 52, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (411, 16, 39, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (412, 16, 55, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (413, 16, 56, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (414, 16, 14, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (415, 16, 15, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (416, 16, 74, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (417, 16, 75, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (418, 16, 77, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (419, 16, 78, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (425, 16, 17, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (426, 16, 18, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (427, 16, 83, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (428, 16, 84, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (429, 16, 86, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (430, 16, 87, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (436, 16, 20, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (437, 16, 21, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (438, 16, 92, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (439, 16, 93, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (440, 16, 95, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (441, 16, 96, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (447, 16, 61, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (448, 16, 62, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (449, 16, 101, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (450, 16, 102, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (451, 16, 104, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (452, 16, 105, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (460, 16, 25, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (461, 16, 111, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (462, 17, 3, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (463, 17, 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (464, 17, 41, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (465, 17, 42, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (466, 17, 43, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (467, 17, 44, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (468, 17, 45, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (469, 17, 5, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (470, 17, 46, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (471, 17, 47, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (472, 17, 48, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (473, 17, 49, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (474, 17, 143, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (475, 17, 144, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (477, 17, 27, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (478, 17, 115, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (479, 17, 116, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (480, 17, 117, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (481, 17, 118, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (482, 17, 29, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (483, 17, 30, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (484, 17, 125, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (485, 17, 126, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (486, 17, 31, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (487, 17, 128, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (488, 17, 129, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (490, 17, 32, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (491, 17, 131, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (492, 17, 132, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (494, 18, 8, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (495, 18, 9, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (496, 18, 57, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (497, 18, 58, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (498, 18, 59, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (499, 18, 10, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (500, 18, 60, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (501, 18, 11, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (502, 18, 12, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (503, 18, 64, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (504, 18, 65, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (505, 18, 66, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (506, 18, 67, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (507, 18, 68, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (508, 18, 69, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (509, 18, 13, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (510, 18, 70, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (511, 18, 71, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (512, 18, 72, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (513, 18, 73, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (514, 19, 8, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (515, 19, 9, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (516, 19, 57, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (517, 19, 58, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (518, 19, 134, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (519, 19, 14, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (520, 19, 15, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (521, 19, 74, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (522, 19, 75, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (523, 19, 76, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (524, 19, 77, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (525, 19, 78, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (526, 19, 16, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (527, 19, 79, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (528, 19, 80, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (529, 19, 81, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (530, 19, 82, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (531, 19, 135, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (532, 20, 14, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (533, 20, 15, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (534, 20, 74, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (535, 20, 75, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (536, 20, 76, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (537, 20, 77, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (538, 20, 78, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (539, 20, 16, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (540, 20, 79, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (541, 20, 80, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (542, 20, 81, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (543, 20, 82, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (544, 21, 20, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (545, 21, 21, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (546, 21, 92, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (547, 21, 93, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (548, 21, 94, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (549, 21, 95, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (550, 21, 96, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (551, 21, 22, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (552, 21, 97, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (553, 21, 98, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (554, 21, 99, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (555, 21, 100, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (556, 21, 25, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (557, 21, 111, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (558, 21, 112, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (559, 22, 20, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (560, 22, 21, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (561, 22, 92, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (562, 22, 93, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (563, 22, 94, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (564, 22, 95, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (565, 22, 96, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (566, 22, 22, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (567, 22, 97, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (568, 22, 98, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (569, 22, 99, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (570, 22, 100, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (571, 22, 25, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (572, 22, 111, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (573, 22, 112, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (574, 23, 61, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (575, 23, 62, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (576, 23, 101, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (577, 23, 102, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (578, 23, 103, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (579, 23, 104, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (580, 23, 105, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (581, 23, 63, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (582, 23, 106, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (583, 23, 107, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (584, 23, 108, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (585, 23, 109, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (586, 23, 23, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (587, 23, 110, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (588, 23, 24, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (589, 23, 113, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (590, 23, 114, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (591, 23, 25, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (592, 23, 111, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (593, 23, 112, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (594, 24, 17, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (595, 24, 18, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (596, 24, 83, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (597, 24, 84, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (598, 24, 85, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (599, 24, 86, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (600, 24, 87, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (601, 24, 19, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (602, 24, 88, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (603, 24, 89, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (604, 24, 90, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (605, 24, 91, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (606, 25, 17, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (607, 25, 18, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (608, 25, 83, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (609, 25, 84, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (610, 25, 85, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (611, 25, 86, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (612, 25, 87, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (613, 25, 19, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (614, 25, 88, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (615, 25, 89, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (616, 25, 90, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (617, 25, 91, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (618, 26, 17, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (619, 26, 18, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (620, 26, 83, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (621, 26, 84, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (622, 26, 85, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (623, 26, 86, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (624, 26, 87, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (625, 26, 19, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (626, 26, 88, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (627, 26, 89, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (628, 26, 90, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (629, 26, 91, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (630, 27, 11, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (631, 27, 12, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (632, 27, 64, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (633, 27, 65, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (634, 27, 67, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (635, 27, 68, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (642, 27, 14, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (643, 27, 15, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (644, 27, 74, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (645, 27, 75, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (646, 27, 77, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (647, 27, 78, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (653, 27, 17, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (654, 27, 18, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (655, 27, 83, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (656, 27, 84, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (657, 27, 86, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (658, 27, 87, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (664, 27, 20, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (665, 27, 21, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (666, 27, 92, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (667, 27, 93, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (668, 27, 95, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (669, 27, 96, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (675, 27, 61, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (676, 27, 62, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (677, 27, 101, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (678, 27, 102, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (679, 27, 104, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (680, 27, 105, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (686, 27, 25, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (687, 27, 111, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (688, 28, 3, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (689, 28, 4, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (690, 28, 41, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (691, 28, 42, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (692, 28, 43, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (693, 28, 44, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (694, 28, 45, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (695, 28, 5, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (696, 28, 46, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (697, 28, 47, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (698, 28, 48, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (699, 28, 49, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (700, 29, 143, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (701, 29, 144, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (703, 29, 136, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (704, 29, 27, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (705, 29, 115, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (706, 29, 116, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (707, 29, 117, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (708, 29, 118, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (709, 29, 119, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (710, 29, 120, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (711, 29, 121, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (712, 29, 28, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (713, 29, 122, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (714, 29, 123, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (715, 29, 124, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (716, 29, 29, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (717, 29, 30, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (718, 29, 125, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (719, 29, 126, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (720, 29, 127, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (721, 29, 31, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (722, 29, 128, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (723, 29, 129, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (725, 29, 32, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (726, 29, 131, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (727, 29, 132, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1165, 2, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1166, 7, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1167, 8, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1168, 9, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1169, 13, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1170, 3, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1171, 15, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1172, 16, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1173, 17, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1174, 21, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1175, 22, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1176, 23, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1177, 27, 137, 0, '2020-03-31 15:25:57', '2020-03-31 15:25:57');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1184, 2, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1185, 3, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1186, 4, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1187, 5, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1188, 6, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1189, 7, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1190, 8, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1191, 9, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1192, 10, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1193, 11, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1194, 12, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1195, 13, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1196, 14, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1197, 15, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1198, 16, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1199, 17, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1200, 18, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1201, 19, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1202, 20, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1203, 21, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1204, 22, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1205, 23, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1206, 24, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1207, 25, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1208, 26, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1209, 27, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1210, 28, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1211, 29, 138, 0, '2020-03-31 15:36:04', '2020-03-31 15:36:04');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1238, 29, 137, 0, '2020-04-01 16:42:59', '2020-04-01 16:43:00');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1239, 3, 139, 0, '2020-04-01 20:47:50', '2020-04-01 20:47:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1240, 15, 139, 0, '2020-04-01 20:47:50', '2020-04-01 20:47:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1241, 17, 139, 0, '2020-04-01 20:47:50', '2020-04-01 20:47:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1242, 29, 139, 0, '2020-04-01 20:47:50', '2020-04-01 20:47:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1268, 3, 141, 0, '2020-04-02 14:48:46', '2020-04-02 14:48:46');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1269, 15, 141, 0, '2020-04-02 14:48:46', '2020-04-02 14:48:46');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1270, 17, 141, 0, '2020-04-02 14:48:46', '2020-04-02 14:48:46');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1271, 29, 141, 0, '2020-04-02 14:48:46', '2020-04-02 14:48:46');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1387, 41, 3, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1388, 41, 4, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1389, 41, 5, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1390, 41, 143, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1391, 41, 27, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1392, 41, 28, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1393, 41, 29, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1394, 41, 30, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1395, 41, 31, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1396, 41, 32, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1397, 41, 41, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1398, 41, 42, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1399, 41, 43, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1400, 41, 44, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1401, 41, 45, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1402, 41, 46, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1403, 41, 47, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1404, 41, 48, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1405, 41, 49, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1406, 41, 144, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1407, 41, 115, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1408, 41, 116, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1409, 41, 117, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1410, 41, 118, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1411, 41, 119, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1412, 41, 120, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1413, 41, 121, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1414, 41, 122, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1415, 41, 123, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1416, 41, 124, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1417, 41, 125, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1418, 41, 126, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1419, 41, 127, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1420, 41, 128, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1421, 41, 129, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1423, 41, 131, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1424, 41, 132, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1426, 41, 136, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1427, 41, 137, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1428, 41, 138, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1429, 41, 139, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1430, 41, 141, 0, '2020-04-02 15:42:48', '2020-04-02 15:42:48');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1431, 42, 3, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1432, 42, 4, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1433, 42, 5, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1434, 42, 143, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1435, 42, 27, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1436, 42, 28, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1437, 42, 29, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1438, 42, 30, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1439, 42, 31, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1440, 42, 32, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1441, 42, 41, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1442, 42, 42, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1443, 42, 43, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1444, 42, 44, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1445, 42, 45, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1446, 42, 46, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1447, 42, 47, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1448, 42, 48, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1449, 42, 49, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1450, 42, 144, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1451, 42, 115, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1452, 42, 116, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1453, 42, 117, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1454, 42, 118, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1455, 42, 119, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1456, 42, 120, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1457, 42, 121, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1458, 42, 122, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1459, 42, 123, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1460, 42, 124, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1461, 42, 125, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1462, 42, 126, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1463, 42, 127, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1464, 42, 128, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1465, 42, 129, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1467, 42, 131, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1468, 42, 132, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1470, 42, 136, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1471, 42, 137, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1472, 42, 138, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1473, 42, 139, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1474, 42, 141, 0, '2020-04-02 15:43:21', '2020-04-02 15:43:21');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1525, 3, 38, 0, '2020-04-03 11:36:14', '2020-04-03 11:36:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1526, 15, 38, 0, '2020-04-03 11:36:14', '2020-04-03 11:36:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1527, 17, 38, 0, '2020-04-03 11:36:14', '2020-04-03 11:36:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1528, 29, 38, 0, '2020-04-03 11:36:14', '2020-04-03 11:36:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1529, 41, 38, 0, '2020-04-03 11:36:14', '2020-04-03 11:36:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1530, 42, 38, 0, '2020-04-03 11:36:14', '2020-04-03 11:36:14');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1531, 3, 145, 0, '2020-04-03 14:57:08', '2020-04-03 14:57:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1532, 15, 145, 0, '2020-04-03 14:57:08', '2020-04-03 14:57:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1533, 17, 145, 0, '2020-04-03 14:57:08', '2020-04-03 14:57:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1534, 29, 145, 0, '2020-04-03 14:57:08', '2020-04-03 14:57:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1535, 41, 145, 0, '2020-04-03 14:57:08', '2020-04-03 14:57:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1536, 42, 145, 0, '2020-04-03 14:57:08', '2020-04-03 14:57:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1537, 3, 142, 0, '2020-04-03 14:59:24', '2020-04-03 14:59:24');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1538, 15, 142, 0, '2020-04-03 14:59:24', '2020-04-03 14:59:24');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1539, 17, 142, 0, '2020-04-03 14:59:24', '2020-04-03 14:59:24');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1540, 29, 142, 0, '2020-04-03 14:59:24', '2020-04-03 14:59:24');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1541, 41, 142, 0, '2020-04-03 14:59:24', '2020-04-03 14:59:24');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1542, 42, 142, 0, '2020-04-03 14:59:24', '2020-04-03 14:59:24');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1543, 3, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1544, 14, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1545, 15, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1546, 17, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1547, 28, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1548, 29, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1549, 41, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1550, 42, 146, 0, '2020-04-07 12:48:43', '2020-04-07 12:48:43');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1551, 3, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1552, 14, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1553, 15, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1554, 17, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1555, 28, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1556, 29, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1557, 41, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1558, 42, 147, 0, '2020-04-07 12:49:08', '2020-04-07 12:49:08');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1559, 3, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1560, 14, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1561, 15, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1562, 17, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1563, 28, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1564, 29, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1565, 41, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1566, 42, 40, 0, '2020-04-07 12:51:50', '2020-04-07 12:51:50');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1749, 15, 148, 0, '2020-04-14 17:17:44', '2020-04-14 17:17:44');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1750, 29, 148, 0, '2020-04-14 17:17:44', '2020-04-14 17:17:44');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1751, 41, 148, 0, '2020-04-14 17:17:44', '2020-04-14 17:17:44');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1752, 42, 148, 0, '2020-04-14 17:17:44', '2020-04-14 17:17:44');
		INSERT INTO `auth_role_func`(`id`, `auth_role_id`, `auth_func_id`, `deleted`, `create_time`, `update_time`) VALUES (1753, 1, 149, 0, '2020-04-15 14:26:46', '2020-04-15 14:26:46');



-- 流程表（flow）：

INSERT INTO `flow`(`id`, `flow_name`, `remark`, `company_capital_id`, `flow_class`, `deleted`, `create_time`, `update_time`) VALUES (1, '用款申请审核', '聚源公司用信申请审核', 1, 'CreditApply', 0, '2020-03-21 21:41:35', '2020-03-21 21:41:37');
INSERT INTO `flow`(`id`, `flow_name`, `remark`, `company_capital_id`, `flow_class`, `deleted`, `create_time`, `update_time`) VALUES (2, '还款确认审核', '聚源公司还款确认审核', 1, 'RepayBill', 0, '2020-03-22 20:28:24', '2020-03-22 20:28:27');


-- 流程节点（flow_node）：

INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (1, '运营审核', 1, 18, '运营审核节点', 0, 2, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 1);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (2, '风控经理审核', 1, 19, '风控一审核节点', 1, 3, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 2);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (3, '风控部长审核', 1, 20, '风控二审核节点', 2, 4, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 2);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (4, '财务经理审核', 1, 21, '财务一审核节点', 3, 5, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 3);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (5, '财务部长审核', 1, 22, '财务二审核节点', 4, 6, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 3);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (6, '管理层副总经理审核', 1, 24, '管理层一审核节点', 5, 7, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 4);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (7, '管理层总经理审核', 1, 25, '管理层二审核节点', 6, 8, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 4);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (8, '管理层执行董事审核', 1, 26, '管理层三审核节点', 7, 9, 0, 0, '0', '2020-03-23 17:06:38', '2020-03-23 17:06:42', 4);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (9, '出纳审核', 1, 23, '出纳审核', 8, 10, 0, 0, '0', '2020-03-23 17:12:01', '2020-03-23 17:12:04', 5);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (10, '放款成功', 1, NULL, '放款成功（完结）', 9, 0, 0, 0, '0', '2020-03-21 21:43:27', '2020-03-21 21:43:27', 6);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (20, '还款中', 2, 23, '还款中节点', 22, 21, 0, 0, '0', '2020-03-22 20:30:52', '2020-03-22 20:30:54', 2);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (21, '已还款', 2, NULL, '已还款节点（完结）', 20, 22, 0, 0, '0', '2020-03-22 20:35:05', '2020-03-22 20:35:07', 3);
INSERT INTO `flow_node`(`id`, `flow_node_name`, `flow_id`, `role_id`, `remark`, `prev_node_id`, `next_node_id`, `flow_type_id`, `limit_balance`, `deleted`, `create_time`, `update_time`, `status`) VALUES (22, '还款拒绝', 2, NULL, '还款拒绝节点（完结）', 20, 0, 0, 0, '0', '2020-03-23 10:05:38', '2020-03-23 10:05:41', -1);



-- 节点类型（flow_node_type）：

INSERT INTO `flow_node_type`(`id`, `type`, `amount`, `remark`, `deleted`, `create_time`, `update_time`) VALUES (0, '0', 1, '单人审核', NULL, NULL, NULL);
INSERT INTO `flow_node_type`(`id`, `type`, `amount`, `remark`, `deleted`, `create_time`, `update_time`) VALUES (1, '1', 2, '多人无序2人审核', NULL, NULL, NULL);
INSERT INTO `flow_node_type`(`id`, `type`, `amount`, `remark`, `deleted`, `create_time`, `update_time`) VALUES (2, '1', 3, '多人有序3人顺序审核', NULL, NULL, NULL);



-- 系统配置（scf_config）：


INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (247, 0, 'CREDIT_REGULATION_CHECK_LIST', 'CREDIT_REGULATION_CHECK_LIST', '用信规则引擎列表', 1, 0, '2020-02-29 09:39:34', '2020-02-29 09:39:37');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (248, 247, 'TRACK_INFO_CHECK', 'TRACK_INFO_CHECK', '是否校验轨迹信息', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (249, 247, 'COMPANY_CREDIT_INVESTIGATION_CHECK', 'COMPANY_CREDIT_INVESTIGATION_CHECK', '是否校验企业征信', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (250, 247, 'TRANSPORTER_CREDIT_INVESTIGATION_CHECK', 'TRANSPORTER_CREDIT_INVESTIGATION_CHECK', '是否校验承运人征信', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (251, 247, 'CREDIT_REGULATION_CHECK', 'CREDIT_REGULATION_CHECK', '是否启用用信规则引擎', 1, 0, '2020-03-13 14:35:09', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (252, 0, 'LOAN_REVIEW_CONFIG_CHECK', '80', '放款审核配置', 1, 0, '2020-03-24 11:02:55', '2020-03-24 11:02:58');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (253, 247, 'TRANSPORTER_VEHICLE_CHECK', 'TRANSPORTER_VEHICLE_CHECK', '是否验证实际承运车辆', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (254, 247, 'WAYBILL_SEND_CHECK', 'WAYBILL_SEND_CHECK', '是否验证运单发货方', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (255, 247, 'WAYBILL_CONSIGNEE_CHECK', 'WAYBILL_CONSIGNEE_CHECK', '是否验证运单收货方', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (256, 247, 'WAYBILL_LOCATION_CHECK', 'WAYBILL_LOCATION_CHECK', '是否验证运单起止点', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (257, 247, 'WAYBILL_TRACK_CHECK', 'WAYBILL_TRACK_CHECK', '是否验证运单运输轨迹', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (258, 247, 'WAYBILL_TIME_CHECK', 'WAYBILL_TIME_CHECK', '是否验证运单时间节点', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (259, 247, 'WAYBILL_DATA_CHECK', 'WAYBILL_DATA_CHECK', '是否验证运单单据数据', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (260, 247, 'WAYBILL_CONTRACT_CHECK', 'WAYBILL_CONTRACT_CHECK', '是否验证运单合同', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (261, 247, 'WAYBILL_PRICE_CHECK', 'WAYBILL_PRICE_CHECK', '是否验证运价信息', 0, 0, '2020-02-29 09:39:34', '2020-03-20 15:36:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (262, 0, 'BAIDU_MAP_URL', 'http://api.map.baidu.com/api?v=2.0&ak=61676PsUUHiTWjuBM4iGSKs9', '百度地图URL', 1, 0, '2020-04-10 10:34:14', '2020-04-10 10:34:16');
INSERT INTO `scf_config`(`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (263, 0, 'SCHEDULE_EXECUTE_HOUR', '16', '定时任务时间', 0, 0, '2020-04-10 10:34:14', '2020-04-10 10:34:14');
INSERT INTO `scf_config` (`id`, `parent_id`, `config_key`, `config_value`, `config_desc`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES ('271', '0', 'MAX_WAYBILL_COUNT_IN_SUBJECT_CLAIMS_ORDER', '600', '单条应收款转让运单包含的最大运单数量', '1', 0, '2020-04-21 18:24:00', '2020-04-21 18:24:03');





-- 管理用户表（scf_user）：

INSERT INTO `scf_user`(`id`, `owner_id`, `user_type`, `role_id`, `dept_id`, `username`, `user_password`, `nick_name`, `company_id`, `company_name`, `user_email`, `user_mobile_no`, `user_avatar`, `deleted`, `last_login_time`, `create_time`, `update_time`) VALUES (1, 1, 1, 1, 1, 'admin', 'be62a3f50a150165a764c60ce0494fe7', '凯特', 1, '资方企业001', '', '13688887777', '', 0, '2020-02-20 12:54:34', '2020-02-20 12:54:29', '2020-02-21 16:41:03');
INSERT INTO `scf_user`(`id`, `owner_id`, `user_type`, `role_id`, `dept_id`, `username`, `user_password`, `nick_name`, `company_id`, `company_name`, `user_email`, `user_mobile_no`, `user_avatar`, `deleted`, `last_login_time`, `create_time`, `update_time`) VALUES (2, 0, 2, 16, NULL, 'jyqy', 'be62a3f50a150165a764c60ce0494fe7', '聚源管理员', NULL, NULL, '', '', '', 0, NULL, '2020-03-27 18:01:02', '2020-03-27 18:01:02');




-- 用户配置关系表（scf_user_config）：

INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (1, 2, 248, '0', 0, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (2, 2, 249, '0', 0, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (3, 2, 250, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:05');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (4, 2, 251, '0', 0, 0, '2020-03-27 18:01:02', '2020-03-27 18:01:02');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (5, 2, 252, '50', 0, 0, '2020-03-27 18:01:02', '2020-04-11 12:13:55');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (6, 2, 253, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:05');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (7, 2, 254, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (8, 2, 255, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (9, 2, 256, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (10, 2, 257, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (11, 2, 258, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (12, 2, 259, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (13, 2, 260, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');
INSERT INTO `scf_user_config`(`id`, `owner_id`, `config_id`, `config_value`, `config_status`, `deleted`, `create_time`, `update_time`) VALUES (14, 2, 261, '0', 0, 0, '2020-03-27 18:01:02', '2020-04-10 13:53:06');

