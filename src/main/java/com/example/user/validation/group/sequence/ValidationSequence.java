/**
 * 
 */
package com.example.user.validation.group.sequence;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.example.user.validation.group.PrimaryValidationGroup;

/**
 * @author Salman.Khandu
 *
 */
@GroupSequence({ Default.class, PrimaryValidationGroup.class })
public interface ValidationSequence {

}
