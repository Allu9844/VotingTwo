package com.guruprasadhiremathgmail.bmsit.activity.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.CandidatesPojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by allam on 6/7/17.
 */

public class RecyclerAdapterBankAccounts extends RecyclerView.Adapter<RecyclerAdapterBankAccounts.RecyclerViewHolder> {


    private Context context;
    private LayoutInflater inflater;
    private String formattedDate;
    private boolean isMember;
    public LinearLayout mRelativeLayout;
    public String mVehicle;
    public ArrayList<Boolean> ckeckbox;
    public static String mBankId;

    ArrayList<CandidatesPojo> mBankData;

    public RecyclerAdapterBankAccounts(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public  void setSchedule(ArrayList<CandidatesPojo> scheduleDatas) {
        mBankData = scheduleDatas;
        ckeckbox = new ArrayList<>();

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.single_row_bank_acounts_details, parent, false);
        RecyclerAdapterBankAccounts.RecyclerViewHolder viewHolder = new RecyclerAdapterBankAccounts.RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

//
        holder.mName.setText(mBankData.get(position).getName());
//        holder.mBankName.setText(mBankData.get(position).getBank_name());
//        holder.mIfsc.setText(mBankData.get(position).getIfsc_code());
        holder.mParty.setText(mBankData.get(position).getParty());

        Picasso.with(context)
                .load(mBankData.get(position).getImage())
                .placeholder(R.drawable.about_vote) //this is optional the image to display while the url image is downloading
                .error(R.drawable.about_vote)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(holder.mLogo);


        holder.mRadioButton.setChecked(mBankData.get(position).is_checked());

        View.OnClickListener mAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"Booing",Toast.LENGTH_LONG).show();


                for(int i=0;i<mBankData.size();i++)
                {
                    mBankData.get(i).setIs_checked(false);
                }

                RecyclerAdapterBankAccounts.RecyclerViewHolder vholder = (RecyclerAdapterBankAccounts.RecyclerViewHolder) v.getTag();
                int position = vholder.getPosition();


                mBankData.get(position).setIs_checked(true);
                notifyDataSetChanged();
                mBankId = mBankData.get(position).getId();
                Log.i("dddddddd","ddddddddddddddddddddddddddddddddddddddddd"+mBankId);


            }
        };

        holder.mRadioButton.setOnClickListener(mAdd);
        holder.mRadioButton.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return mBankData.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView mName,mIfsc,mParty,mBankName;
        public RadioButton mRadioButton;
        public ImageView mLogo;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

            mName = (TextView)itemView.findViewById(R.id.name);
            mIfsc = (TextView)itemView.findViewById(R.id.ifsc);
            mParty = (TextView)itemView.findViewById(R.id.party);
            mBankName = (TextView)itemView.findViewById(R.id.bank_name);
            mRadioButton = (RadioButton)itemView.findViewById(R.id.radio_button);
            mLogo = (ImageView)itemView.findViewById(R.id.logo);
        }
    }
}


