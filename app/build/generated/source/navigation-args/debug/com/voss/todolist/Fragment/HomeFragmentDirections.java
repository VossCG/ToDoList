package com.voss.todolist.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.voss.todolist.Adapter.ArgsToContent;
import com.voss.todolist.R;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static ActionHomeFragmentToContentFragment actionHomeFragmentToContentFragment(
      @NonNull ArgsToContent ContentArgs) {
    return new ActionHomeFragmentToContentFragment(ContentArgs);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToBrowseEventFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_browseEventFragment);
  }

  public static class ActionHomeFragmentToContentFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToContentFragment(@NonNull ArgsToContent ContentArgs) {
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ContentArgs", ContentArgs);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToContentFragment setContentArgs(@NonNull ArgsToContent ContentArgs) {
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ContentArgs", ContentArgs);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("ContentArgs")) {
        ArgsToContent ContentArgs = (ArgsToContent) arguments.get("ContentArgs");
        if (Parcelable.class.isAssignableFrom(ArgsToContent.class) || ContentArgs == null) {
          __result.putParcelable("ContentArgs", Parcelable.class.cast(ContentArgs));
        } else if (Serializable.class.isAssignableFrom(ArgsToContent.class)) {
          __result.putSerializable("ContentArgs", Serializable.class.cast(ContentArgs));
        } else {
          throw new UnsupportedOperationException(ArgsToContent.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_homeFragment_to_contentFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public ArgsToContent getContentArgs() {
      return (ArgsToContent) arguments.get("ContentArgs");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToContentFragment that = (ActionHomeFragmentToContentFragment) object;
      if (arguments.containsKey("ContentArgs") != that.arguments.containsKey("ContentArgs")) {
        return false;
      }
      if (getContentArgs() != null ? !getContentArgs().equals(that.getContentArgs()) : that.getContentArgs() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getContentArgs() != null ? getContentArgs().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToContentFragment(actionId=" + getActionId() + "){"
          + "ContentArgs=" + getContentArgs()
          + "}";
    }
  }
}
