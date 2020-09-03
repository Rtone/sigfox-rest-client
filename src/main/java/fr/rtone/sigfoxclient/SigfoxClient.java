/**
 * Copyright 2020 Rtone
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.rtone.sigfoxclient;

import fr.rtone.sigfoxclient.model.*;
import retrofit2.http.*;
import rx.Observable;

import java.util.List;

import static fr.rtone.sigfoxclient.util.SigfoxApiConstants.*;


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
     * @return an empty result
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
     * @return an empty result
     */
    @POST(DEVICE_TYPE_API + "/edit")
    Observable<Void> editDeviceType(@Body DeviceType deviceType);

    /**
     * Delete an existing device type
     *
     * @param deviceType device type to edit
     * @return an empty result
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
     * @return an empty result
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks/{callbackId}/delete")
    Observable<Void> deleteCallback(@Path("deviceTypeId") String deviceTypeId, @Path("callbackId") String callbackId);

    /**
     * Enable or disable an existing callback
     *
     * @param deviceTypeId device type id
     * @param callbackId   callback id
     * @param enabled      true to enable the callback, false to disable it
     * @return an empty result
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks/{callbackId}/enable")
    Observable<Void> enableCallback(@Path("deviceTypeId") String deviceTypeId, @Path("callbackId") String callbackId, @Query("enabled") boolean enabled);

    /**
     * The given callback will be selected as the downlink one
     *
     * @param deviceTypeId device type id
     * @param callbackId  callback id
     * @return an empty result
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/callbacks/{callbackId}/downlink")
    Observable<Void> setDownlinkCallback(@Path("deviceTypeId") String deviceTypeId, @Path("callbackId") String callbackId);

    /**
     * Get the list of devices by device type
     *
     * @param deviceTypeId device type id
     * @param snr          optional, filter the device list according to the average signal to noise ratio of the last 25 received messages
     * @param limit        maximum number of status events to return, default 100
     * @param offset       number of devices to skip (between 0 and 5000)
     * @return array of device objects
     */
    @GET(DEVICE_TYPE_API + "/{deviceTypeId}/devices")
    Observable<SigfoxData<Device>> getDeviceList(@Path("deviceTypeId") String deviceTypeId, @Query("snr") String snr, @Query("limit") Integer limit, @Query("offset") Integer offset);

    /**
     * Get device Information
     *
     * @param deviceId device id
     * @return device object
     */
    @GET(DEVICE_API + "/{deviceId}")
    Observable<Device> getDevice(@Path("deviceId") String deviceId);

    /**
     * Creates new devices by providing a list of identifier, the registration is performed in async way.
     *
     * @param deviceTypeId    device type Id
     * @param registerRequest identifiers of the devices to be created
     * @return device registration response with registration job id to check whether the registration has success or not.
     */
    @POST(DEVICE_TYPE_API + "/{deviceTypeId}/devices/bulk/create/async")
    Observable<DeviceRegisterResponse> registerNewDevices(@Path("deviceTypeId") String deviceTypeId, @Body DeviceRegisterRequest registerRequest);

    /**
     * Check if the registration is success or not
     *
     * @param deviceTypeId device type id
     * @param jobId        job id returned by the creation service
     * @return the status of the given devices creation job
     */
    @GET(DEVICE_TYPE_API + "/{deviceTypeId}/registration/{jobId}")
    Observable<DeviceRegistrationStatus> checkDevicesRegistration(@Path("deviceTypeId") String deviceTypeId, @Path("jobId") String jobId);

    /**
     * Edit a list of devices
     *
     * @param devices a list of devices to be edited
     * @return return edition operation status
     */
    @POST(DEVICE_API + "/bulk/edit")
    Observable<DeviceEditResponse> editDeviceList(@Body List<Device> devices);

    /**
     * Get the messages that were sent by a device, in reverse chronological order (most recent messages first).
     *
     * @param deviceId device id
     * @return Messages list
     */
    @GET(DEVICE_API + "/{deviceId}/messages")
    Observable<SigfoxData<Message>> getMessages(@Path("deviceId") String deviceId);

}
