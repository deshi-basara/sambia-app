import mongoose from 'mongoose';

/**
 * Activity Schema.
 *
 * Database schema for on activity. An activity has to be in a group and
 * can have a items describing the activity.
 */
const ActivitySchema = new mongoose.Schema({

  name: {
    type: String,
    required: true,
  },
  translation: {
    type: String,
    required: false,
    default: '',
  },
  image: {
    type: String,
    required: false,
  },
  items: {
    type: [mongoose.Schema.Types.ObjectId],
    ref: 'Item',
    required: false,
    default: [],
  },
  enabled: {
    type: Boolean,
    required: true,
    default: false,
  },
  createdAt: {
    type: Date,
    default: Date.now,
  },

});

/**
 * @typedef Activity
 */
export default mongoose.model('Activity', ActivitySchema);
