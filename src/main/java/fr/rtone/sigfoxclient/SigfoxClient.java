package fr.rtone.sigfoxclient;

import fr.rtone.sigfoxclient.model.Callback;
import fr.rtone.sigfoxclient.model.DeviceType;
import fr.rtone.sigfoxclient.model.Group;
import fr.rtone.sigfoxclient.model.SigfoxData;
import retrofit2.http.*;
import rx.Observable;

import java.util.List;

import static fr.rtone.sigfoxclient.util.SigfoxApiConstants.DEVICE_TYPE_API;
import static fr.rtone.sigfoxclient.util.SigfoxApiConstants.GROUPS_API;

/**
 * @Author: Hani
 */
public interface SigfoxClient {

    /**
     * Get the list of all groups
     *
     * @param limit    maximum number of groups to return, ignored if null
     * @param offset   number of groups skipped, ignored if null
     * @param parentId id of the group for which the group list must be retrieved, ignored if null
     * @return array of group objects
     */
    @GET(GROUPS_API)
    Observable<SigfoxData<Group>> getGroupList(@Query("limit") Integer limit, @Query("offset") Integer offset,
                                               @Query("parentId") String parentId);

    /**
     * Get the description of a specific group
     *
     * @param groupId the group identifier
     * @return group object
     */
    @GET(GROUPS_API + "/{groupId}")
    Observable<Group> getGroupById(@Path("groupId") String groupId);

    /**
     * Create a new group
     *
     * @param group group to create
     * @return created group with identifier
     */
    @POST(GROUPS_API + "/create")
    Observable<Group> createGroup(@Body Group group);

    /**
     * Edit an existing group
     *
     * @param group group to edit
     * @return edited group with identifier
     */
    @POST(GROUPS_API + "/edit")
    Observable<Group> editGroup(@Body Group group);

    /**
     * Delete an existing group
     *
     * @param group group to delete
     */
    @POST(GROUPS_API + "/delete")
    Observable<Void> deleteGroup(@Body Group group);

    /**
     * Get the list of all Device types
     *
     * @param includeSubGroups if need also device types from child groups
     * @return array of device types objects
     */
    @GET(DEVICE_TYPE_API)
    Observable<SigfoxData<DeviceType>> getDeviceTypeList(@Query("includeSubGroups") Boolean includeSubGroups);

    /**
     * Get the description of a specific device type
     *
     * @param devicetypeId the device type identifier
     * @return device type object
     */
    @GET(DEVICE_TYPE_API + "/{devicetypeId}")
    Observable<DeviceType> getDeviceType(@Path("devicetypeId") String devicetypeId);

    /**
     * Create a new device type
     *
     * @param deviceType device type to create
     * @return created device type with identifier
     */
    @POST(DEVICE_TYPE_API + "/create")
    Observable<DeviceType> createDeviceType(@Body DeviceType deviceType);

    /**
     * Edit an existing group
     *
     * @param deviceType device type to edit
     */
    @POST(DEVICE_TYPE_API + "/edit")
    Observable<Void> editDeviceType(@Body DeviceType deviceType);

    /**
     * Delete an existing device type
     *
     * @param deviceType device type to edit
     */
    @POST(DEVICE_TYPE_API + "/delete")
    Observable<Void> deleteDeviceType(@Body DeviceType deviceType);

    /**
     * Get the list of callbacks by device type
     *
     * @param deviceTypeId device type id
     * @return array of callback objects
     */
    @GET(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks")
    Observable<SigfoxData<Callback>> getCallbackList(@Path("deviceTypeId") String deviceTypeId);

    /**
     * Create a new callback
     *
     * @param deviceTypeId device type id
     * @param callbacks    callback to create
     * @return array of created callback's ids
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks/new")
    Observable<List<String>> createCallbackList(@Path("deviceTypeId") String deviceTypeId, @Body List<Callback> callbacks);

    /**
     * Delete an existing callback
     *
     * @param deviceTypeId device type id
     * @param callbackId   callback id
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks/{callbackId}/delete")
    Observable<Void> deleteCallback(@Path("deviceTypeId") String deviceTypeId, @Path("callbackId") String callbackId);

    /**
     * Enable or disable an existing callback
     *
     * @param deviceTypeId device type id
     * @param callbackId   callback id
     * @param enabled      true to enable the callback, false to disable it
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks/{callbackId}/enable")
    Observable<Void> enableCallback(@Path("deviceTypeId") String deviceTypeId, @Path("callbackId") String callbackId, @Query("enabled") boolean enabled);

    /**
     * The given callback will be selected as the downlink one
     *
     * @param deviceTypeId device type id
     * @param callbackId
     * @return
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks/{callbackId}/downlink")
    Observable<Void> setDownlinkCallback(@Path("deviceTypeId") String deviceTypeId, @Path("callbackId") String callbackId);

}
