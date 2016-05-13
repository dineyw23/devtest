package com.build.devtest;

import java.util.ArrayList;
import java.util.List;

/**
 *  ParentViewMapperImpl implements ParentViewMapper public interface 
 *  which creates List of ParentViews.
 */

public class ParentViewMapperImpl implements ParentViewMapper {

    /**
     *  @desc :   Iterate over List of ParentRow to create ParentViews and
     *           then iterate over List of ChildRow to create ChildViews
     *           associated with that parentId.
     *
     *  @param :  parentRows - List of ParentRow objects
     *  @param :  childRows -  List of ChildRow objects
     *  @return : List of ParentView objects
     */

	public List<ParentView> mapRowsToViews(List<ParentRow> parentRows,
                                           List<ChildRow> childRows) {

		List<ParentView> parentViewList = new ArrayList<ParentView>();

		for (ParentRow p : parentRows) {

			ParentView newParentView = processParent(p);
			List<ChildView> childViewsList = new ArrayList<ChildView>();

			for (ChildRow c : childRows) {

                /* parentId of Parent equals to parentId of the Child */
                if (c.getParentId().equalsIgnoreCase(p.getParentId())) {

                    ChildView newChildView = processChild(c, newParentView);
                    childViewsList.add(newChildView);
                }
            }
			newParentView.setChildViews(childViewsList);
			parentViewList.add(newParentView);
		}

		return parentViewList;
	}

    /**
     *   Creates and returns ParentView using data from ParentRow
     *
     *   @param : p - ParentRow object
     *   @return : ParentView Object
     */

    private ParentView processParent(ParentRow p) {

        return new ParentView(p.getFirstName(),
                p.getLastName(),
                p.getAge(),
                p.getParentId(),
                new ArrayList<ChildView>()
        );
    }

    /**
     *   Creates ChildView having the associated ParenView object
     *
     *    @param : c - ChildRow object
     *    @param : newParentView - ParentView object associated with ChildView
     *    @return : ChildView object with all the data
     */

    private ChildView processChild(ChildRow c, ParentView newParentView) {

        return new ChildView(c.getFirstName(),
                c.getLastName(),
                c.getAge(),
                c.getParentId(),
                newParentView
        );
    }
}