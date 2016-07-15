import mongoose from 'mongoose';

/**
 * Subject Schema.
 *
 * Database schema for a subject.
 */
const SubjectSchema = new mongoose.Schema({

  name: {
    type: String,
    required: true,
  },
  image: {
    type: String,
    required: false,
  },
  age: {
    type: Number,
    required: true,
  },
  gender: {
    type: String,
    required: true,
  },
  education: {
    type: String,
    required: true,
  },
  tribe: {
    type: String,
    required: true,
  },
  household: {
    type: Number,
    required: true,
  },
  size: {
    type: Number,
    required: true,
  },
  weight: {
    type: Number,
    required: true,
  },
  createdAt: {
    type: Date,
    default: Date.now,
  },

});

/**
 * @typedef Subject
 */
export default mongoose.model('Subject', SubjectSchema);
