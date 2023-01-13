package com.ebrem.cse476sonassignment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ebrem.cse476sonassignment.fragments.TAB1;
import com.ebrem.cse476sonassignment.fragments.TAB2;
import com.ebrem.cse476sonassignment.fragments.TAB3;
import com.ebrem.cse476sonassignment.fragments.TAB4;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TAB1();
            case 1:
                return new TAB2();
            case 2:
                return new TAB3();
            case 3:
                return new TAB4();
            default:
                return new TAB1();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
