package com.mana.hadoop;

import com.mana.hadoop.word.WordCountMapper;
import com.mana.hadoop.word.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Word count.
 */
public class WordCount {
    private static final Logger log = LoggerFactory.getLogger(WordCount.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        // Ensure that input and output paths are provided
        if (args.length < 2) {
            System.err.println("Usage: WordCount <input path> <output path>");
            System.exit(-1);
        }

        // Get input and output paths from arguments
        String inputPath = args[0];
        String outputPath = args[1];

        // Log the input and output paths for confirmation
        log.info("Input path: {}, Output path: {}", inputPath, outputPath);

        // Set up Hadoop configuration and create a new job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Word Count");
        job.setJarByClass(WordCount.class);

        // Set Mapper and Reducer classes
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // Set output key and value types for Mapper and Reducer
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Note ensure the input path does not have inner folders
        // Set input and output paths for the job
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // Submit job and wait for completion
        boolean completed = job.waitForCompletion(true);
        log.info("Job completed successfully: {}", completed);
    }
}
