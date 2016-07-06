import mongoose from 'mongoose';

/**
 * Group Schema.
 *
 * Database schema for a group of activities.
 */
const GroupSchema = new mongoose.Schema({

  name: {
    type: String,
    required: true,
  },
  translation: {
    type: String,
    required: true,
    default: {},
  },
  image: {
    type: String,
    required: true,
  },
  activity: {
    type: [Number],
    required: true,
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
 * @typedef Group
 */
export default mongoose.model('Group', GroupSchema);
