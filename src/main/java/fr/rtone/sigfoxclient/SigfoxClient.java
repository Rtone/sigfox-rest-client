package fr.rtone.sigfoxclient;

import fr.rtone.sigfoxclient.model.SigfoxData;
import fr.rtone.sigfoxclient.model.group.Group;
import retrofit2.http.*;
import rx.Observable;

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
     * @param group
     * @return edited group with identifier
     */
    @POST(GROUPS_API + "/edit")
    Observable<Group> editGroup(@Body Group group);

    /**
     * Delete an existing group
     *
     * @param group
     */
    @POST(GROUPS_API + "/delete")
    Observable<Void> deleteGroup(@Body Group group);

}
