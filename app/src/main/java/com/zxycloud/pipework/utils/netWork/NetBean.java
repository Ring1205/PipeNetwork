package com.zxycloud.pipework.utils.netWork;

import android.content.Context;

import com.zxycloud.pipework.BuildConfig;
import com.zxycloud.pipework.event.type.NetType;
import com.zxycloud.pipework.event.type.SpType;
import com.zxycloud.pipework.utils.SPUtils;

/**
 * @author leiming
 * @date 2019/3/20.
 */
public class NetBean {

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public static final String devHost = "http://192.168.32.133/";
    public static final String testHost = "https://zxyun119.com/";
    public static final String releaseHost = "https://zxyun119.com/";

    /**
     * 获取host
     *
     * @return host
     */
    public static String getHost() {
        String host;
        if (BuildConfig.DEBUG) {
//            host = testHost;
            host = devHost;
//            host = releaseHost;
        } else {
            host = releaseHost;
        }
        return host;
    }

    /**
     * 获取当前的Host
     * 服务器配置会影响默认Url，因此不能使用getHost
     *
     * @return host
     */
    public static String getCurrentUrl() {
        return SPUtils.getInstance().getString(SpType.URL, getHost().concat("AppHzyControllerApi/1.0/")).replace("AppHzyControllerApi/1.0/", "");
    }

    /*--------------------------------------------请求路径-----------------------------------------------*/
    public static String getUrl(int actionType, String action) {
        switch (actionType) {
            // 通用业务请求
            case NetType.API_TYPE_NORMAL:
                return String.format("%s%s", SPUtils.getInstance().getString(SpType.URL, getHost().concat("AppHzyControllerApi/1.0/")), action);

            // 文件操作（上传，下载）
            case NetType.API_TYPE_FILE_OPERATION:
                return String.format("%s%s", SPUtils.getInstance().getString(SpType.FILE_UPLOAD, getHost().concat("fileUpload/1.0/")), action);

            // 统计接口
            case NetType.API_TYPE_STATISTICS:
                return String.format("%s%s", SPUtils.getInstance().getString(SpType.STATISTICS, getHost().concat("StatisticsController/1.0/")), action);

            // 巡查
            case NetType.API_TYPE_PATROL:
                return String.format("%s%s", SPUtils.getInstance().getString(SpType.PATROL, getHost().concat("patrolApi/1.0/")), action);

            // 登录设置
            case NetType.API_TYPE_LOGIN_SETTING:
                return action;

            default:
                break;
        }
        return action;
    }

    public static void save(SystemSettingBean bean) {
        SPUtils spUtils = SPUtils.getInstance();
        String url = spUtils.getString("login_complete_url", getHost());
        spUtils.put(SpType.URL, url.concat(bean.getBusinessUrl()));
        spUtils.put(SpType.FILE_UPLOAD, url.concat(bean.getFileUploadUrl()));
        spUtils.put(SpType.PATROL, url.concat(bean.getPatrolUrl()));
        spUtils.put(SpType.STATISTICS, url.concat(bean.getStatisticsUrl()));
    }

    /*-----------------------------------------------尾址-----------------------------------------------*/

    /**
     * 登录设置
     */
    public static final String actionAppConfig = "appConfig.json";
    /*-----------------------------------------------登录-----------------------------------------------*/
    /**
     * 登录
     */
    public static final String actionGetToken = "token/getToken";
    /**
     * 根据token登录
     */
    public static final String actionSignInByTokenId = "signInByTokenId";
    /**
     * 登录
     */
    public static final String actionSignIn = "signIn";
    /**
     * 登出
     */
    public static final String actionSignOut = "signOut";
    /**
     * 首次登录时提示后台返回需要修改密码时的修改个人信息接口
     */
    public static final String actionUpdateUserPasswordAndNickName = "updateUserPasswordAndNickName";
    /**
     * 获取注册图形验证码
     */
    public static final String actionGetSignUpCaptcha = "signUp/captcha/get";
    /**
     * 获取注册短信/邮箱验证码
     */
    public static final String actionGetSignUpVerification = "signUp/verification/get";
    /**
     * 验证根据短信/邮箱验证码获取随机码
     */
    public static final String actionGetSignUpRandomCode = "signUp/randomCode/get";
    /**
     * 注册
     */
    public static final String actionSignUp = "signUp";
    /**
     * 注销账号
     */
    public static final String actionLogout = "user/logOutUserBase";

    /**
     * 获取忘记密码图形验证码
     */
    public static final String actionGetPasswordCaptcha = "password/captcha/get";
    /**
     * 获取修改密码短信/邮箱验证码
     */
    public static final String actionGetPasswordVerification = "password/verification/get";
    /**
     * 验证根据短信/邮箱验证码获取随机码
     */
    public static final String actionGetPasswordRandomCode = "password/randomCode/get";
    /**
     * 通过短信修改密码
     */
    public static final String actionResetPasswordByPhone = "password/reset/byRandomCode";
    /**
     * 通过旧密码修改密码
     */
    public static final String actionResetPasswordByOld = "password/reset/byOld";

    /**
     * 获取修改邮箱图形验证码
     */
    public static final String actionGetUpdateEmailCaptcha = "updateEmail/captcha/get";
    /**
     * 获取手机验证码
     */
    public static final String actionGetUpdateEmailVerification = "updateEmail/verification/get";
    /**
     * 邮箱验证随机码获取
     */
    public static final String actionGetUpdateEmail = "updateEmail/randomCode/get";

    /*-----------------------------------------------业务-----------------------------------------------*/
    /**
     * 验证版本升级
     */
    public static final String actionCheckAppVersion = "appVersion/getAppVersionInfo";
    /**
     * 获取场所列表（添加设备时使用——包含场所平面图与联网设备数量）
     */
    public static final String actionPostPlaceList = "place/list";
    /**
     * 获取简单场所列表（通用列表）
     */
    public static final String actionPostSimplePlaceList = "place/getSimplePlaceList";
    /**
     * 添加场所
     */
    public static final String actionPostPlaceAdd = "place/add";
    /**
     * 编辑场所
     */
    public static final String actionPostPlaceEdit = "place/edit";
    /**
     * 删除场所
     */
    public static final String actionGetPlaceDelete = "place/delete";
    /**
     * 获取场所下联系人列表
     */
    public static final String actionGetPlaceLinkmanList = "place/linkman/list";
    /**
     * 添加场所联系人
     */
    public static final String actionAddPlaceLinkman = "place/linkman/add";
    /**
     * 删除场所联系人
     */
    public static final String actionGetDeleteContacts = "place/linkman/delete";
    /**
     * 获取场所详情
     */
    public static final String actionGetPlaceDetail = "place/info";
    /**
     * 获取单位详情
     */
    public static final String actionGetProjectDetail = "project/info/detail";
    /**
     * 获取单位列表
     */
    public static final String actionGetProjectList = "project/getList";
    /**
     * 获取设备列表
     */
    public static final String actionPostDeviceList = "device/deviceState/list";
    /**
     * 根据设备所属系统获取设备列表
     */
    public static final String actionGetDeviceListBySystemId = "device/deviceSystem/list";
    /**
     * 根据非直联设备列表
     */
    public static final String actionGetDeviceListNetworkNo = "device/nonDirect/list";
    /**
     * 获取设备系统
     */
    public static final String actionGetBelongDeviceSystem = "device/belongDeviceSystem/list";
    /**
     * 获取网关下的设备列表
     */
    public static final String actionPostAdapterDeviceList = "device/adapterId/list";
    /**
     * 根据父设备ID获取下一级子设备列表(新增)
     */
    public static final String actionPostParentDeviceIdList = "device/parentDeviceId/list";
    /**
     * 获取网关下的设备列表
     */
    public static final String actionPostPlaceDeviceList = "device/placeId/list";
    /**
     * 设备取消分配
     */
    public static final String actionPostDeviceUnassign = "device/cancelDistribution";
    /**
     * 删除设备
     */
    public static final String actionPostDeleteDeviceInfo = "device/deleteDeviceInfo";
    /**
     * 获取设备详情
     */
    public static final String actionGetDeviceDetail = "device/info";
    /**
     * 设备下发命令
     */
    public static final String actionSendCommand = "device/sendCommond";
    /**
     * 设备型号列表接口
     */
    public static final String actionGetUnitMetadataNoAllList = "deviceUnit/getDeviceUnitMetadataNoAllList";
    /**
     * 根据设备Id获取通道列表
     */
    public static final String actionGetChannelListByDevice = "device/channel/list";
    /**
     * 根据设备Id获取通道列表
     */
    public static final String actionPostDeleteChannel = "device/deleteChannel";
    /**
     * 3191下发布防/撤防命令
     */
    public static final String actionGetSendDefenceCommandToDevice = "device/sendDefenceCommandToDevice";
    /**
     * 3191获取设备布防配置
     */
    public static final String actionGetDefenceConfigByDeviceId = "device/getDefenceConfigByDeviceId";
    /**
     * 3191下发布防配置
     */
    public static final String actionGetSsendDefenceConfigToDevice = "device/sendDefenceConfigToDevice";
    /**
     * 获取运行记录列表
     */
    public static final String actionGetRecordList = "record/list";
    /**
     * 获取实时运行记录列表
     */
    public static final String actionGetRealTimeRecordList = "record/realTimeList";
    /**
     * 获取离线记录列表
     */
    public static final String actionGetOfflineDeviceList = "record/offlineDeviceList";
    /**
     * 获取事件记录列表
     */
    public static final String actionPostEventRecordList = "record/getEventListById";
    /**
     * 获取事件复核结果列表
     */
    public static final String actionGetEventReviewResultList = "event/getProcessTypeList";
    /**
     * 执行事件复核
     */
    public static final String actionPostEventReview = "event/eventProcess";
    /**
     * 获取父级区域列表
     */
    public static final String actionGetParentAreaList = "area/getAreaList";
    /**
     * 根据条件获取片区列表
     */
    public static final String actionGetAreaMenuList = "mobilePatrolPoint/getAreaMenuList";
    /**
     * 获取区域列表
     */
    public static final String actionPostAreaList = "area/list";
    /**
     * 获取区域详情
     */
    public static final String actionGetAreaDetail = "area/info";
    /**
     * 添加区域
     */
    public static final String actionPostAddArea = "area/add";
    /**
     * 删除区域
     */
    public static final String actionGetDeleteArea = "area/delete";
    /**
     * 获取视频列表
     */
    public static final String actionGetVideoList = "video/getlist";
    /**
     * 获取设备安装详情
     */
    public static final String actionGetDeviceInstallDetail = "device/intallationDetail/info";
    /**
     * 根据视频Id获取视频地址
     */
    public static final String actionGetVideoPathByCamera = "video/getVideoInfoByIdNoDev";
    /**
     * 根据设备信息获取视频地址
     */
    public static final String actionGetVideoPathByDevice = "video/getVideoInfoByDevId";
    /**
     * 根据设备信息获取萤石登录token
     */
    public static final String actionGetVideoYSToken = "video/YSlogin";
    /**
     * 获取告警详情
     */
    public static final String actionGetAlertDetail = "record/detail";
    /**
     * 获取实时告警详情
     */
    public static final String actionGetRealTimeAlertDetail = "record/getRealTimeDetail";
    /**
     * 火警复核
     */
    public static final String actionGetAlertReview = "FireAlertMonitor/fireAlertProcess";
    /**
     * 获取复核结果列表
     */
    public static final String actionGetProcessTypeList = "FireAlertMonitor/getProcessTypeList";
    /**
     * 设置激光推送Id
     */
    public static final String actionClearPushInfo = "clearAppUserToken";
    /**
     * 添加隐患
     */
    public static final String actionAddRiskRecord = "hiddenRecord/addHiddenRecord";
    /**
     * 获取隐患列表
     */
    public static final String actionRiskList = "hiddenRecord/getHiddenRecordList";
    /**
     * 获取隐患列表
     */
    public static final String actionRiskNoticeList = "hiddenRecord/getHiddenNotificationList";
    /**
     * 获取隐患详情
     */
    public static final String actionRiskDetail = "hiddenRecord/getHiddenRecordById";
    /**
     * 获取当前隐患是否可以分配给一级区域管理员整改
     */
    public static final String actionGetRiskCanDistribution = "hiddenRecord/getDistributionState";
    /**
     * 分配当前隐患给一级区域管理员
     */
    public static final String actionGetRiskDistribution = "hiddenRecord/editPrincipalUser";
    /**
     * 获取能够分配的一级区域列表
     */
    public static final String actionGetRiskDistributionAreaList = "hiddenRecord/getPrincipalList";
    /**
     * 判断当前隐患是否可以整改
     */
    public static final String actionGetRiskCanProcess = "hiddenRecord/getDisposeState";
    /**
     * 获取能够操作的整改类型列表
     */
    public static final String actionGetCanProcessStateList = "hiddenRecord/getHiddenProcessState";
    /**
     * 获取隐患整改进度列表
     */
    public static final String actionGetRiskProgressList = "hiddenProcess/getHiddenProcessListByHiddenId";
    /**
     * 获取整改流程的详情
     */
    public static final String actionGetRiskProcessDetail = "hiddenProcess/getHiddenProcessById";
    /**
     * 提交隐患整改信息
     */
    public static final String actionPostRiskProcessReport = "hiddenProcess/addHiddenProcess";
    /**
     * 提交消防监管下发隐患整改信息
     */
    public static final String actionPostSuperviseProcessReport = "message/checkRectify";
    /**
     * 验证网关
     */
    public static final String actionPostVerifyAdapter = "device/validateAdapterDistribution";
    /**
     * 根据网关名称查询设备列表(用户添加设备)
     */
    public static final String getActionPostAdapterDeviceList = "device/distribution/list";
    /**
     * 获取设备平面图点位
     */
    public static final String actionGetLayerPoint = "device/layerPoint";
    /**
     * 添加设备获取系统列表
     */
    public static final String actionGetDeviceSystem = "deviceSystem/list";
    /**
     * 单个图片上传接口(增加返回图片宽高以及压缩图路径)
     */
    public static final String actionPostUploadImg = "upload/compressImg";
    /**
     * 单个视频音频上传
     */
    public static final String actionPostUploadFlie = "upload/video";
    /**
     * 删除已上传的附件
     */
    public static final String actionPostDeleteFlie = "upload/delFileByUrl";
    /**
     * 分配设备
     */
    public static final String actionPostAllocateDevice = "device/distribution";

    /**
     * 巡查任务列表
     */
    public static final String actionPostPatrolTaskList = "mobilePatrolTask/getPatrolTaskList";

    /**
     * 巡查任务详情
     */
    public static final String actionGetTaskDetails = "mobilePatrolTask/getTaskByTaskId";

    /**
     * 任务巡检点详细信息
     */
    public static final String actionPostTaskPointDetails = "mobilePatrolTask/getTaskPointByTaskPoint";

    /**
     * 提交任务巡查点的巡查报告
     */
    public static final String actionPostEditTask = "mobilePatrolTask/editTaskPointByTaskPointId";

    /**
     * 扫描获取任务巡查列表
     */
    public static final String actionPostPatrolTask = "mobilePatrolTask/getTaskByTPtagNumber";

    /**
     * 巡查点查询列表
     */
    public static final String actionPostPatrolPointList = "mobilePatrolPoint/getPatrolPointList";

    /**
     * 巡查点查询单条数据
     */
    public static final String actionGetPatrolPointDetails = "mobilePatrolPoint/getPatrolPointById";

    /**
     * 添加巡查点
     */
    public static final String actionPostMobilePatrolPoint = "mobilePatrolPoint/addPatrolPoint";

    /**
     * 巡查标签重复验证
     */
    public static final String actionGetTagNumberVerify = "patrolPoint/getTagNumberVerify";

    /**
     * 修改巡查点
     */
    public static final String actionPostEditPatrolPointById = "mobilePatrolPoint/editPatrolPointById";

    /**
     * 获取巡查项类型列表
     */
    public static final String actionGetPatrolTypeMenu = "mobilePatrolMenu/getPatrolTypeMenuList";

    /**
     * 获取打卡类型列表(new)
     */
    public static final String actionGetCardTypeMenuList = "mobilePatrolMenu/getCardTypeMenuList";

    /**
     * 获取巡查项分类列表（设备分类）(new)
     */
    public static final String actionGetEquTypeMenu = "mobilePatrolMenu/getEquTypeMenuList";

    /**
     * 通过类型集合获取巡查项集合
     */
    public static final String actionPostPatrolByType = "mobilePatrolItem/getPatrolItemListByType";

    /**
     * 获取最近七天或15天日志列表
     */
    public static final String actionRecentAlarm = "underProject/homepage/getRecentAlarmDefaultList";

    /**
     * 获取一段时间内火警复核的结果（真实、误报、测试、未复核）的占比，以及火警/预警数
     */
    public static final String actionFireInfo = "statis/getAppFireInfo";

    /**
     * 获取一段时间内火警复核的结果列表
     */
    public static final String actionFireList = "statis/getAppFireList";

    /**
     * 获取近N个月的复核情况
     */
    public static final String actionGetMedia = "patrol/getMediaByTaskPointId";

    /**
     * 获取最新第一优先级状态
     */
    public static final String actionGetFirstState = "record/getTopLevelStateRecord";

    /**
     * 获取近N个月的复核情况
     */
    public static final String actionFireReviewInfo = "statis/getAppFireStatisticsInfoByYearMonth";

    /**
     * 获取近N个月的故障数
     */
    public static final String actionFaultInfo = "statis/getAppFaultStatisticsInfoByYearMonth";

    /**
     * 获取近N个月的故障设备列表
     */
    public static final String actionFaultDeviceListInfo = "statis/getAppFaultListByProjectIdAndTime";

    /**
     * 获取各种设备类型发生的告警数
     */
    public static final String actionAlarmByDeviceType = "statis/getAppAlarmAnalysisByDeviceTypeNow";

    /**
     * 获取近N个月的隐患以及整改情况
     */
    public static final String actionAlarmFalseInfo = "statis/getAppFalseAlarmAnalysisCountInDays";

    /**
     * 获取近N个月的隐患以及整改情况
     */
    public static final String actionRiskInfo = "statis/getHiddenStatisticsInfoByYearMonth";

    /**
     * 获取单位下巡查情况
     */
    public static final String actionPatrolInfo = "statis/getPatrolStatistics";

    /**
     * 获取单位直联设备在线情况
     */
    public static final String actionDeviceOnlineInfo = "statis/getAppDeviceOnLineStatistics";
    /**
     * 获取各状态日志数统计
     */
    public static final String actionAppHead = "underProject/homepage/getAppHead";
    /**
     * 头部 - 设备运行状态及接入场所数量
     */
    public static final String actionGetAlarmMubmer = "HomePageStatistics/project/getDeviceRunningStateAndPlaceCountByProjectId";

    /**
     * 获取单位直联设备列表
     */
    public static final String actionOnlineAdapterList = "statis/getAppAdapterList";

    /**
     * 获取常见问题列表
     */
    public static final String actionGetQuestionList = "other/getQuestionPage";
    /**
     * 获取小知识列表
     */
    public static final String actionGetKnowledgeList = "other/getKnowledge";
    /**
     * 获取隐私条款
     */
    public static final String actionGetPrivacyPolicy = "other/getTermsOfService";
    /**
     * 根据项目id获取项目统计6项信息
     */
    public static final String actionGetStatisticsNumber = "underProject/homepage/single/getManageRegionHead";
    /**
     * 添加区域/场所时查询用户列表
     */
    public static final String actionGetUserList = "user/getUserList";
    /**
     * 设备通道类型列表(新增)
     */
    public static final String actionGetSensorTagList = "sensorTag/list";
    /**
     * 通道详情(新增)
     */
    public static final String actionGetChannelDetail = "device/getChannelDetail";
    /**
     * 通道编辑(新增)
     */
    public static final String actionPostEditChannel = "device/editChannel";
    /**
     * 添加通道(新增)
     */
    public static final String actionPostAddChannelInDevice = "device/addChannelInDevice";
    /**
     * 获取设备类型列表
     */
    public static final String actionGetDeviceTypeList = "deviceType/list";
    /**
     * 获取用户待办隐含数量(红点提示)
     */
    public static final String actionGetHiddenRecord = "hiddenRecord/getHiddenReminder";
    /**
     * 获取4~20mA设备采集值
     */
    public static final String actionGetDeviceCollectConfig = "device/getDeviceCollectConfigByAdapterName";
    /**
     * 4-20mA设备下发配置信息
     */
    public static final String actionPostSendConfigCommandToDevice = "device/sendConfigCommandToDevice";
    /**
     * 疏散灯具命令下发
     */
    public static final String actionPostSendCommandToDeviceWorkModle = "device/sendCommondToDeviceWorkModle";
    /*---------------------------------------------社区版新增---------------------------------------------*/
    /**
     * 获取监控区域统计情况
     */
    public static final String actionGetManageRegionHead = "underProject/homepage/getManageRegionHead";
    /**
     * 获取各状态日志数统计
     */
    public static final String actionGetAppHead = "underProject/homepage/getAppHead";
    /**
     * 获取各单位火警、预警、故障概况统计列表
     */
    public static final String actionGetFireWarnFaultProjectSummaryList = "record/catalog/getFireWarnFaultProjectSummaryList";
    /**
     * 获取各单位事件概况统计列表
     */
    public static final String actionGetEventProjectSummaryList = "record/catalog/getEventProjectSummaryList";
    /**
     * 获取各单位隐患概况统计列表
     */
    public static final String actionGetRiskProjectSummaryList = "record/catalog/getHiddenProjectSummaryList";
    /**
     * 获取各单位离线概况统计列表
     */
    public static final String actionGetOfflineProjectSummaryList = "record/catalog/getOfflineDeviceProjectSummaryList";
    /**
     * 获取各单位一级区域概况统计列表
     */
    public static final String actionGetAreaProjectSummaryList = "record/catalog/getFirstLevelAreaProjectSummaryList";
    /**
     * 获取各单位场所概况统计列表
     */
    public static final String actionGetPlaceProjectSummaryList = "record/catalog/getPlaceProjectSummaryList";
    /**
     * 获取各单位设备概况统计列表
     */
    public static final String actionGetDeviceProjectSummaryList = "record/catalog/getDeviceProjectSummaryList";
    /**
     * 社区版-最新消息列表
     */
    public static final String actionGetCommunityRecentMessageList = "message/getCommunityRecentMessageList";
    /**
     * 社区版-消息-系统、监管、整改、新闻消息列表
     */
    public static final String actionGetRegulatoryMessageList = "message/getRegulatoryMessageList";
    /**
     * 判断双随机隐患是否可以整改
     */
    public static final String actionGetRegulatoryStateById = "message/getRegulatoryStateById";
    /*-----------------------------------------------防打扰-----------------------------------------------*/
    /**
     * 获取防打扰场所列表
     */
    public static final String actionPostAntiDisturbancePlaceList = "antiDisturbance/placeList";
    /**
     * 获取场所下防打扰模式列表
     */
    public static final String actionGetAntiDisturbanceGetConfiguration = "antiDisturbance/configuration/get";
    /**
     * 删除防打扰时间段
     */
    public static final String actionGetAntiDisturbanceDeleteConfiguration = "antiDisturbance/configuration/delete";
    /**
     * 添加防打扰时间段
     */
    public static final String actionGetAntiDisturbanceAddConfiguration = "antiDisturbance/configuration/add";
    /**
     * 获取防打扰场所列表
     */
    public static final String actionGetAntiDisturbanceUpdateConfiguration = "antiDisturbance/configuration/update";

}
